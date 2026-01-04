package com.example.expense_tracker.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.expense_tracker.dto.CategoryDto;
import com.example.expense_tracker.entity.CategoryEntity;
import com.example.expense_tracker.repository.CategoryRepository;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public void addCategory(CategoryDto categoryDto) {
        CategoryEntity entity = new CategoryEntity(categoryDto);
        this.categoryRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public List<CategoryEntity> getAllCategories() {
        return this.categoryRepository.findAll();
    }
}
