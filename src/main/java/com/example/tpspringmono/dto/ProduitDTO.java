package com.example.tpspringmono.dto;

import com.example.tpspringmono.entities.Category;
import com.example.tpspringmono.entities.Produit;
import com.example.tpspringmono.repos.CategoryRepo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProduitDTO(int id, @NotBlank String reference, @NotBlank String nom,
                         @Size(max = 100) String description, double price, int stock,
                         Integer categoryId) {


    static public ProduitDTO toDto(Produit produit) {
        Integer catId = produit.getCat() == null ? null : produit.getCat().getId();
        ProduitDTO prod = new ProduitDTO(produit.getId(), produit.getReference(), produit.getNom(),
                produit.getDescription(), produit.getPrice(), produit.getStock(), catId);
        return prod;
    }


    public Produit fromDto(CategoryRepo categoryRepo) {
        Produit produit = new Produit();
        produit.setId(id == 0 ? null : id);
        produit.setReference(reference);
        produit.setNom(nom);
        produit.setDescription(description);
        produit.setPrice(price);
        produit.setStock(stock);

        if (categoryId != null) {
            Category category = categoryRepo.findById(categoryId).orElse(null);
            produit.setCat(category);
        }
        return produit;
    }
}
