package com.example.demo.service;


import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public List<Category> getAll() {
        return (List<Category>) categoryRepository.findAll();
    }

    public Category getById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    public Category update(Category category) {
        return categoryRepository.save(category);
    }

    public void delete(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }


}
