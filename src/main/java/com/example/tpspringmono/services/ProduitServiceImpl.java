package com.example.tpspringmono.services;

import com.example.tpspringmono.dto.ProduitDTO;
import com.example.tpspringmono.entities.Produit;
import com.example.tpspringmono.repos.CategoryRepo;
import com.example.tpspringmono.repos.ProduitRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitServiceImpl implements ProduitService {
    private final ProduitRepo produitRepo;
    private final CategoryRepo categoryRepo;

    public ProduitServiceImpl(ProduitRepo produitRepo, CategoryRepo categoryRepo) {
        this.produitRepo = produitRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public ResponseEntity<ProduitDTO> save(ProduitDTO produitDTO) {
        Produit produit = produitDTO.fromDto(categoryRepo);
        produitRepo.save(produit);
        return ResponseEntity.ok().body(ProduitDTO.toDto(produit));
    }

    @Override
    public ResponseEntity<ProduitDTO> update(int id, ProduitDTO produitDTO) {
        Produit exProduit = produitRepo.findById(id)
                .orElse(null);
        exProduit.setReference(produitDTO.reference());
        exProduit.setNom(produitDTO.nom());
        exProduit.setDescription(produitDTO.description());
        exProduit.setPrice(produitDTO.price());
        exProduit.setStock(produitDTO.stock());
        if (produitDTO.categoryId() != null) {
            exProduit.setCat(categoryRepo.findById(produitDTO.categoryId()).orElse(null));
        }
        produitRepo.save(exProduit);
        return ResponseEntity.ok(ProduitDTO.toDto(exProduit));
    }

    @Override
    public void delete(int id) {
        produitRepo.deleteById(id);
    }

    @Override
    public ProduitDTO findById(int id) {
        return ProduitDTO.toDto(produitRepo.findById(id).get());
    }

    @Override
    public List<ProduitDTO> findByCategoryId(int id) {
        return produitRepo.findByCatId(id).stream().map(ProduitDTO::toDto).toList();
    }

    @Override
    public List<ProduitDTO> findByNom(String nom) {
        return produitRepo.findByNomContaining(nom).stream().map(ProduitDTO::toDto).toList();
    }
}
