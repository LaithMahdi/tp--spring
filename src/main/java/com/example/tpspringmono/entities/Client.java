package com.example.tpspringmono.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;

    private String prenom;

    @Column(nullable = false, unique = true)
    private String email;

    private String telephone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idAdresse")
    private Adresse adresse;

    @OneToMany(mappedBy = "client")
    private List<Commande> commandes = new ArrayList<Commande>();
}
