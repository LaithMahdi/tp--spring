package com.example.tpspringmono.services;

import com.example.tpspringmono.dto.CommandeDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommandeService {
    ResponseEntity<CommandeDTO> save(CommandeDTO commandeDTO);
    CommandeDTO findById(int id);
    List<CommandeDTO> findByClientId(int clientId);
}
