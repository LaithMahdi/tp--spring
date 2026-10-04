package com.example.tpspringmono.controllers;

import com.example.tpspringmono.dto.CategoryDTO;
import com.example.tpspringmono.entities.Category;
import com.example.tpspringmono.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<Category> createCategory(@RequestBody @Valid Category category) {
         return categoryService.save(category);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@RequestParam int id, @RequestBody @Valid Category category) {
        return categoryService.update(id, category);
    }


    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@RequestParam int id) {
        return categoryService.findById(id);
    }


}
