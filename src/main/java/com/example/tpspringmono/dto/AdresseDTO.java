package com.example.tpspringmono.dto;

import com.example.tpspringmono.entities.Adresse;

public record AdresseDTO(Integer id, String rue, String ville, String codePostal, String pays) {

    static public AdresseDTO toDto(Adresse adresse) {
        if (adresse == null) {
            return null;
        }
        return new AdresseDTO(adresse.getId(), adresse.getRue(), adresse.getVille(),
                adresse.getCodePostal(), adresse.getPays());
    }


    public Adresse fromDto() {
        Adresse adresse = new Adresse();
        adresse.setId(id == null || id == 0 ? null : id);
        adresse.setRue(rue);
        adresse.setVille(ville);
        adresse.setCodePostal(codePostal);
        adresse.setPays(pays);
        return adresse;
    }
}
