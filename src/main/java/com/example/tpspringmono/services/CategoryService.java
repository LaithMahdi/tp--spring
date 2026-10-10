package com.example.tpspringmono.services;

import com.example.tpspringmono.dto.CategoryDTO;
import org.springframework.http.ResponseEntity;

public interface CategoryService {
    ResponseEntity<CategoryDTO> save(CategoryDTO categoryDTO);
    CategoryDTO findById(int id);
    ResponseEntity<CategoryDTO> update(int id, CategoryDTO categoryDTO);
    void delete(int id);
}
