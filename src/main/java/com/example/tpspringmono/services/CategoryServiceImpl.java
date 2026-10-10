package com.example.tpspringmono.services;


import com.example.tpspringmono.dto.CategoryDTO;
import com.example.tpspringmono.entities.Category;
import com.example.tpspringmono.repos.CategoryRepo;
import com.example.tpspringmono.repos.ProduitRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;
    private final ProduitRepo produitRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo, ProduitRepo produitRepo) {
        this.categoryRepo = categoryRepo;
        this.produitRepo = produitRepo;
    }

    @Override
    public ResponseEntity<CategoryDTO> save(CategoryDTO categoryDTO) {
        Category category = categoryDTO.fromDto(produitRepo);
        category.setId(null);
        categoryRepo.save(category);
        return ResponseEntity.ok().body(CategoryDTO.toDto(category));
    }

    @Override
    public CategoryDTO findById(int id) {
        return CategoryDTO.toDto(categoryRepo.findById(id).get());
    }

    @Override
    public ResponseEntity<CategoryDTO> update(int id, CategoryDTO categoryDTO) {
        Category exCat = categoryRepo.findById(id)
                .orElse(null);
        exCat.setNom(categoryDTO.nom());
        exCat.setDescription(categoryDTO.description());
        categoryRepo.save(exCat);
        return ResponseEntity.ok(CategoryDTO.toDto(exCat));
    }

    @Override
    public void delete(int id) {
        Category category = categoryRepo.findById(id).orElse(null);
        if (category != null && !category.getProduits().isEmpty()) {
            throw new IllegalStateException("Impossible de supprimer une categorie qui contient des produits.");
        }
        categoryRepo.deleteById(id);
    }
}
