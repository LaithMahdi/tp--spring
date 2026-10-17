package com.example.tpspringmono.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "lignes_commande")
@Data
public class LigneCommande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int quantite;

    private double prixUnitaire;

    @ManyToOne
    @JoinColumn(name = "idCommande")
    private Commande commande;

    private int idP;
    @Transient
    private Produit produit;
}
