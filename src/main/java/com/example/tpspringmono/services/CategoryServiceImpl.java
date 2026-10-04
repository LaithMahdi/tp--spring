package com.example.tpspringmono.services;


import com.example.tpspringmono.dto.CategoryDTO;
import com.example.tpspringmono.entities.Category;
import com.example.tpspringmono.repos.CategoryRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private final CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public ResponseEntity<Category> save( Category category) {
        categoryRepo.findById(category.getId())
                .ifPresent(existingCategory -> {
                    throw new IllegalArgumentException("Category with ID " + category.getId() + " already exists.");
                });
         categoryRepo.save(category);
         return ResponseEntity.ok().body(category);
    }

    @Override
    public CategoryDTO findById(int id) {
     return   CategoryDTO.toDto(categoryRepo.findById(id).get());

    }

    @Override
    public ResponseEntity<Category> update(int id, Category category) {
        Category exCat = categoryRepo.findById(id)
                .orElse(null);
        exCat.setNom(category.getNom());
        exCat.setDescription(category.getDescription());
        categoryRepo.save(exCat);
        return ResponseEntity.ok(exCat);
    }

    @Override
    public void delete(int id) {
        categoryRepo.deleteById(id);
    }
}
