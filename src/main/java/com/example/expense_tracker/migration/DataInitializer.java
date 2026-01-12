package com.example.expense_tracker.migration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.expense_tracker.entity.CategoryEntity;
import com.example.expense_tracker.entity.SubCategoryEntity;
import com.example.expense_tracker.repository.CategoryRepository;
import com.example.expense_tracker.repository.SubCategoryRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository; 

    public DataInitializer(CategoryRepository categoryRepository, SubCategoryRepository subCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            categoryRepository.save(new CategoryEntity("Food & Drink", "category-food"));
            categoryRepository.save(new CategoryEntity("Transport", "category-transport"));
            categoryRepository.save(new CategoryEntity("Bills & Utilities", "category-bill"));
            categoryRepository.save(new CategoryEntity("Entertainment", "category-entertainment"));
            categoryRepository.save(new CategoryEntity("Health", "category-health"));
            categoryRepository.save(new CategoryEntity("Fitness", "category-fitness"));
            categoryRepository.save(new CategoryEntity("Beauty", "category-beauty"));
            categoryRepository.save(new CategoryEntity("Shopping", "category-shopping"));
            categoryRepository.save(new CategoryEntity("Travel", "category-travel"));
            categoryRepository.save(new CategoryEntity("Education", "category-education"));
            categoryRepository.save(new CategoryEntity("Others", "category-others"));

            subCategoryRepository.save(new SubCategoryEntity("KFC", "sub-category-others", categoryRepository.findByName("Food & Drink")));
        }
    }
}
