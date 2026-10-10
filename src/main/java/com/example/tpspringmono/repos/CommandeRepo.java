package com.example.tpspringmono.repos;

import com.example.tpspringmono.entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeRepo extends JpaRepository<Commande, Integer> {
    List<Commande> findByClientId(int id);
}
