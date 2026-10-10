package com.example.tpspringmono.services;

import com.example.tpspringmono.dto.ProduitDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProduitService {
    ResponseEntity<ProduitDTO> save (ProduitDTO produitDTO);
    ResponseEntity<ProduitDTO> update(int id, ProduitDTO produitDTO);
    void delete(int id);
    ProduitDTO findById(int id);
    List<ProduitDTO> findByCategoryId(int id);
    List<ProduitDTO> findByNom(String nom);
}
