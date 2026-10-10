package com.example.tpspringmono.services;

import com.example.tpspringmono.dto.ClientDTO;
import org.springframework.http.ResponseEntity;

public interface ClientService {
    ResponseEntity<ClientDTO> save(ClientDTO clientDTO);
    ClientDTO findById(int id);
    ResponseEntity<ClientDTO> update(int id, ClientDTO clientDTO);
    void delete(int id);
}
