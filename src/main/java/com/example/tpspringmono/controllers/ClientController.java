package com.example.tpspringmono.controllers;

import com.example.tpspringmono.dto.ClientDTO;
import com.example.tpspringmono.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity<ClientDTO> createClient(@RequestBody @Valid ClientDTO clientDTO) {
        return clientService.save(clientDTO);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable int id, @RequestBody @Valid ClientDTO clientDTO) {
        return clientService.update(id, clientDTO);
    }


    @GetMapping("/{id}")
    public ClientDTO getClientById(@PathVariable int id) {
        return clientService.findById(id);
    }


    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable int id) {
        clientService.delete(id);
    }
}
