package com.example.tpspringmono.controllers;

import com.example.tpspringmono.dto.CommandeDTO;
import com.example.tpspringmono.services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commandes")
public class CommandeController {
    @Autowired
    private CommandeService commandeService;

    @PostMapping("/create")
    public ResponseEntity<CommandeDTO> createCommande(@RequestBody CommandeDTO commandeDTO) {
        return commandeService.save(commandeDTO);
    }


    @GetMapping("/{id}")
    public CommandeDTO getCommandeById(@PathVariable int id) {
        return commandeService.findById(id);
    }


    @GetMapping("/client/{clientId}")
    public List<CommandeDTO> getCommandesByClient(@PathVariable int clientId) {
        return commandeService.findByClientId(clientId);
    }
}
