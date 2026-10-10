package com.example.tpspringmono.dto;

import com.example.tpspringmono.entities.Client;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record ClientDTO(Integer id, @NotBlank String nom, @NotBlank String prenom,
                        @NotBlank @Email String email, String telephone,
                        @Valid AdresseDTO adresse, List<Integer> commandes) {


    static public ClientDTO toDto(Client client) {
        List<Integer> ids = client.getCommandes().stream().map(c -> c.getId()).toList();
        ClientDTO cli = new ClientDTO(client.getId(), client.getNom(), client.getPrenom(),
                client.getEmail(), client.getTelephone(), AdresseDTO.toDto(client.getAdresse()), ids);
        return cli;
    }


    public Client fromDto() {
        Client client = new Client();
        client.setId(id == null || id == 0 ? null : id);
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setEmail(email);
        client.setTelephone(telephone);
        client.setAdresse(adresse == null ? null : adresse.fromDto());
        return client;
    }
}
