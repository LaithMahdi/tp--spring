package com.example.tpspringmono.controllers;

import com.example.tpspringmono.dto.CategoryDTO;
import com.example.tpspringmono.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody @Valid CategoryDTO categoryDTO) {
        return categoryService.save(categoryDTO);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable int id, @RequestBody @Valid CategoryDTO categoryDTO) {
        return categoryService.update(id, categoryDTO);
    }


    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable int id) {
        return categoryService.findById(id);
    }


    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable int id) {
        categoryService.delete(id);
    }
}