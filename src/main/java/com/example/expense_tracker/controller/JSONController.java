package com.example.expense_tracker.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.expense_tracker.entity.CategoryEntity;
import com.example.expense_tracker.entity.SubCategoryEntity;
import com.example.expense_tracker.service.CategoryService;
import com.example.expense_tracker.service.SubCategoryService;

@RestController
public class JSONController {
    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;

    public JSONController(CategoryService categoryService, SubCategoryService subCategoryService) {
        this.categoryService = categoryService;
        this.subCategoryService = subCategoryService;
    }

    @GetMapping("/categoriesJSON")
    public List<CategoryEntity> categoriesJSON(Model model) {
        List<CategoryEntity> categories = this.categoryService.getAllCategoriesOrderByOthersLast();
        return categories;
    }

    @GetMapping("/subCategoriesJSON")
    public List<SubCategoryEntity> subCategoriesJSON(@RequestParam String category, Model model) {
        List<SubCategoryEntity> subCategories = this.subCategoryService.getSubCategoriesByCategoryName(category);
        return subCategories;
    }
}
