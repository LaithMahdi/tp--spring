package com.example.tpspringmono.dto;

import com.example.tpspringmono.entities.LigneCommande;

public record LigneCommandeDTO(Integer id, int quantite, Double prixUnitaire, Integer produitId) {


    static public LigneCommandeDTO toDto(LigneCommande ligne) {
        Integer produitId = ligne.getProduit() == null ? null : ligne.getProduit().getId();
        LigneCommandeDTO lig = new LigneCommandeDTO(ligne.getId(), ligne.getQuantite(),
                ligne.getPrixUnitaire(), produitId);
        return lig;
    }
}
