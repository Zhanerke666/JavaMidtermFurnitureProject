package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAllByCategoryId(Long categoryId){
       return productRepository.findAllByCategoryId(categoryId); }

    public List<Product> getAll() {
        return productRepository.findAll();
    }


    public Product getById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Product update(Product product) {
        return productRepository.save(product);
    }

    public void delete(Long productId) {
        productRepository.deleteById(productId);
    }

}
