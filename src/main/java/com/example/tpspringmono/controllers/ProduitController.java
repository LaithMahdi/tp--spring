package com.example.tpspringmono.controllers;

import com.example.tpspringmono.dto.ProduitDTO;
import com.example.tpspringmono.services.ProduitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produits")
public class ProduitController {
    @Autowired
    private ProduitService produitService;

    @PostMapping("/create")
    public ResponseEntity<ProduitDTO> createProduit(@RequestBody @Valid ProduitDTO produitDTO) {
        return produitService.save(produitDTO);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProduitDTO> updateProduit(@PathVariable int id, @RequestBody @Valid ProduitDTO produitDTO) {
        return produitService.update(id, produitDTO);
    }


    @GetMapping("/{id}")
    public ProduitDTO getProduitById(@PathVariable int id) {
        return produitService.findById(id);
    }


    @GetMapping("/categorie/{id}")
    public List<ProduitDTO> getProduitsByCategory(@PathVariable int id) {
        return produitService.findByCategoryId(id);
    }


    @GetMapping("/recherche")
    public List<ProduitDTO> getProduitsByNom(@RequestParam String nom) {
        return produitService.findByNom(nom);
    }


    @DeleteMapping("/{id}")
    public void deleteProduit(@PathVariable int id) {
        produitService.delete(id);
    }
}