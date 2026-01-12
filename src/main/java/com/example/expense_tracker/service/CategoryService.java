package com.example.expense_tracker.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.expense_tracker.entity.CategoryEntity;
import com.example.expense_tracker.repository.CategoryRepository;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public void addCategory(CategoryEntity categoryEntity) {
        this.categoryRepository.save(categoryEntity);
    }

    @Transactional(readOnly = true)
    public List<CategoryEntity> getAllCategories() {
        return this.categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public CategoryEntity getCategoryByName(String name) {
        return this.categoryRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public List<CategoryEntity> getAllCategoriesOrderByOthersLast() {
        return this.categoryRepository.findAllOrderByOthersLast();
    }
}
