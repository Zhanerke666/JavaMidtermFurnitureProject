package com.example.demo.controller;


import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path="/api/product/category/{categoryId}")
    public ResponseEntity<?> findAllByCategoryId(@PathVariable Long categoryId ) {
       return ResponseEntity.ok ( productService.findAllByCategoryId ( categoryId ) ); }


    @GetMapping(path="/api/product")
    public ResponseEntity<?> getStudents() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/api/product/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @PostMapping("/api/product")
    public ResponseEntity<?> save(@RequestBody Product product ) {
        return ResponseEntity.ok(productService.create(product));
    }

    @PutMapping("/api/product")
    public ResponseEntity<?> update(@RequestBody Product product ) {
        return ResponseEntity.ok(productService.update(product));
    }

    @DeleteMapping("/api/product/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
