package com.example.tpspringmono.repos;

import com.example.tpspringmono.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepo extends JpaRepository<Produit, Integer> {
    List<Produit> findByNomContaining(String nom);
    List<Produit> findByCatId(int id);
}
