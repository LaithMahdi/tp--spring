package com.example.tpspringmono.entities;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="catalogue-sevice",url = "http://localhost:9090/produits")
public interface ProduitFeign {

    @PostMapping("/create")
    public ResponseEntity<Produit> createProduit(@RequestBody @Valid Produit produitDTO);


    @PutMapping("/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable int id, @RequestBody @Valid Produit produit) ;

    @GetMapping("/{id}")
    public Produit getProduitById(@PathVariable int id) ;


    @GetMapping("/categorie/{id}")
    public List<Produit> getProduitsByCategory(@PathVariable int id);


    @GetMapping("/recherche")
    public List<Produit> getProduitsByNom(@RequestParam String nom) ;

    @DeleteMapping("/{id}")
    public void deleteProduit(@PathVariable int id);
}
