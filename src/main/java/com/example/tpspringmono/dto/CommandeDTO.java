package com.example.tpspringmono.dto;

import com.example.tpspringmono.entities.Commande;
import com.example.tpspringmono.entities.Statut;

import java.time.LocalDate;
import java.util.List;

public record CommandeDTO(Integer id, LocalDate dateCommande, Statut statut, Double montantTotal,
                          Integer clientId, List<LigneCommandeDTO> lignes) {


    static public CommandeDTO toDto(Commande commande) {
        List<LigneCommandeDTO> ligneDTOS = commande.getLignes().stream()
                .map(LigneCommandeDTO::toDto).toList();
        Integer clientId = commande.getClient() == null ? null : commande.getClient().getId();
        CommandeDTO cmd = new CommandeDTO(commande.getId(), commande.getDateCommande(),
                commande.getStatut(), commande.getMontantTotal(), clientId, ligneDTOS);
        return cmd;
    }
}
