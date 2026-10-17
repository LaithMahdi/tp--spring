package com.example.tpspringmono.services;

import com.example.tpspringmono.dto.CommandeDTO;
import com.example.tpspringmono.dto.LigneCommandeDTO;
import com.example.tpspringmono.entities.*;
import com.example.tpspringmono.repos.ClientRepo;
import com.example.tpspringmono.repos.CommandeRepo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommandeServiceImpl implements CommandeService {
    private final CommandeRepo commandeRepo;
    private final ClientRepo clientRepo;
    private final ProduitFeign produitRepo;

    public CommandeServiceImpl(CommandeRepo commandeRepo, ClientRepo clientRepo, ProduitFeign produitRepo) {
        this.commandeRepo = commandeRepo;
        this.clientRepo = clientRepo;
        this.produitRepo = produitRepo;
    }

    @Override
    @Transactional
    public ResponseEntity<CommandeDTO> save(CommandeDTO commandeDTO) {
        Commande commande = new Commande();
        commande.setDateCommande(LocalDate.now());
        commande.setStatut(Statut.CREEE);
        commande.setClient(clientRepo.findById(commandeDTO.clientId())
                .orElseThrow(() -> new IllegalArgumentException("Client introuvable.")));

        double montantTotal = 0;
        List<LigneCommande> lignes = new ArrayList<LigneCommande>();
        for (LigneCommandeDTO ligneDTO : commandeDTO.lignes()) {
            Produit produit = produitRepo.getProduitById(ligneDTO.id());

            if (produit == null ){
                throw new IllegalArgumentException("Produit introuvable.");
            }



            if (produit.getStock() < ligneDTO.quantite()) {
                throw new IllegalStateException("Stock insuffisant pour le produit " + produit.getReference() + ".");
            }

            produit.setStock(produit.getStock() - ligneDTO.quantite());
            produitRepo.updateProduit(produit.getId() ,produit);

            LigneCommande ligne = new LigneCommande();
            ligne.setQuantite(ligneDTO.quantite());
            ligne.setPrixUnitaire(produit.getPrice());
            ligne.setProduit(produit);
            ligne.setCommande(commande);
            lignes.add(ligne);

            montantTotal += ligne.getQuantite() * ligne.getPrixUnitaire();
        }

        commande.setLignes(lignes);
        commande.setMontantTotal(montantTotal);
        commandeRepo.save(commande);
        return ResponseEntity.ok().body(CommandeDTO.toDto(commande));
    }

    @Override
    public CommandeDTO findById(int id) {
        return CommandeDTO.toDto(commandeRepo.findById(id).get());
    }

    @Override
    public List<CommandeDTO> findByClientId(int clientId) {
        return commandeRepo.findByClientId(clientId).stream().map(CommandeDTO::toDto).toList();
    }
}
