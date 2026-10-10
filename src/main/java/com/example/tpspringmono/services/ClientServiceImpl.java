package com.example.tpspringmono.services;

import com.example.tpspringmono.dto.ClientDTO;
import com.example.tpspringmono.entities.Client;
import com.example.tpspringmono.repos.ClientRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepo clientRepo;

    public ClientServiceImpl(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    @Override
    public ResponseEntity<ClientDTO> save(ClientDTO clientDTO) {
        if (clientRepo.existsByEmail(clientDTO.email())) {
            throw new IllegalArgumentException("Un client avec l'email " + clientDTO.email() + " existe deja.");
        }
        Client client = clientDTO.fromDto();
        clientRepo.save(client);
        return ResponseEntity.ok().body(ClientDTO.toDto(client));
    }

    @Override
    public ClientDTO findById(int id) {
        return ClientDTO.toDto(clientRepo.findById(id).get());
    }

    @Override
    public ResponseEntity<ClientDTO> update(int id, ClientDTO clientDTO) {
        Client exClient = clientRepo.findById(id)
                .orElse(null);
        exClient.setNom(clientDTO.nom());
        exClient.setPrenom(clientDTO.prenom());
        exClient.setEmail(clientDTO.email());
        exClient.setTelephone(clientDTO.telephone());
        if (clientDTO.adresse() != null) {
            exClient.setAdresse(clientDTO.adresse().fromDto());
        }
        clientRepo.save(exClient);
        return ResponseEntity.ok(ClientDTO.toDto(exClient));
    }

    @Override
    public void delete(int id) {
        clientRepo.deleteById(id);
    }
}
