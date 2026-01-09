package com.example.expense_tracker.migration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.expense_tracker.dto.CategoryDto;
import com.example.expense_tracker.entity.CategoryEntity;
import com.example.expense_tracker.repository.CategoryRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public DataInitializer(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            categoryRepository.save(new CategoryEntity(new CategoryDto("Food & Drink", "category-food", "1")));
            categoryRepository.save(new CategoryEntity(new CategoryDto("Transport", "category-transport", "1")));
            categoryRepository.save(new CategoryEntity(new CategoryDto("Bills & Utilities", "category-bill", "1")));
            categoryRepository.save(new CategoryEntity(new CategoryDto("Entertainment", "category-entertainment", "1")));
            categoryRepository.save(new CategoryEntity(new CategoryDto("Health", "category-health", "1")));
            categoryRepository.save(new CategoryEntity(new CategoryDto("Fitness", "category-fitness", "1")));
            categoryRepository.save(new CategoryEntity(new CategoryDto("Beauty", "category-beauty", "1")));
            categoryRepository.save(new CategoryEntity(new CategoryDto("Shopping", "category-shopping", "1")));
            categoryRepository.save(new CategoryEntity(new CategoryDto("Travel", "category-travel", "1")));
            categoryRepository.save(new CategoryEntity(new CategoryDto("Education", "category-education", "1")));
            categoryRepository.save(new CategoryEntity(new CategoryDto("Others", "category-others", "1")));

            categoryRepository.save(new CategoryEntity(new CategoryDto("KFC", "sub-category-others", "2")));
        }
    }
}
