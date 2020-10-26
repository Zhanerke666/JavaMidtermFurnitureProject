package com.example.demo.controller;


import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(path="/api/category")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/api/category/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @PostMapping("/api/category")
    public ResponseEntity<?> save(@RequestBody Category category ) {
        return ResponseEntity.ok(categoryService.create(category));
    }

    @PutMapping("/api/category")
    public ResponseEntity<?> update(@RequestBody Category category ) {
        return ResponseEntity.ok(categoryService.update(category));
    }

    @DeleteMapping("/api/category/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

}
