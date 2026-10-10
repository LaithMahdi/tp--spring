package com.example.tpspringmono.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "adresses")
@Data
public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String rue;

    private String ville;

    private String codePostal;

    private String pays;
}
