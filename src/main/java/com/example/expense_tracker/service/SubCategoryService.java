package com.example.expense_tracker.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.expense_tracker.entity.SubCategoryEntity;
import com.example.expense_tracker.repository.SubCategoryRepository;

@Service
public class SubCategoryService {
    private final SubCategoryRepository subCategoryRepository;

    public SubCategoryService(SubCategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
    }

    @Transactional
    public void addSubCategory(SubCategoryEntity subCategoryEntity) {
        this.subCategoryRepository.save(subCategoryEntity);
    }

    @Transactional(readOnly = true)
    public List<SubCategoryEntity> getAllSubCategories() {
        return this.subCategoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<SubCategoryEntity> getSubCategoriesByCategoryName(String categoryName) {
        return this.subCategoryRepository.findByCategoryName(categoryName);
    }

    @Transactional(readOnly = true)
    public SubCategoryEntity getSubCategoryByName(String name) {
        return this.subCategoryRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public SubCategoryEntity getSubCategoryByNameAndCategoryName(String name, String categoryName) {
        return this.subCategoryRepository.findByNameAndCategoryName(name, categoryName);
    }
}
