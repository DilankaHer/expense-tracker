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
            categoryRepository.save(new CategoryEntity(new CategoryDto("Food & Drink", "category-food")));
            categoryRepository.save(new CategoryEntity(new CategoryDto("Transport", "category-transport")));
            categoryRepository.save(new CategoryEntity(new CategoryDto("Bills & Utilities", "category-bill")));
            categoryRepository.save(new CategoryEntity(new CategoryDto("Entertainment", "category-entertainment")));
            categoryRepository.save(new CategoryEntity(new CategoryDto("Health", "category-health")));
            categoryRepository.save(new CategoryEntity(new CategoryDto("Fitness", "category-fitness")));
            categoryRepository.save(new CategoryEntity(new CategoryDto("Beauty", "category-beauty")));
            categoryRepository.save(new CategoryEntity(new CategoryDto("Shopping", "category-shopping")));
            categoryRepository.save(new CategoryEntity(new CategoryDto("Travel", "category-travel")));
            categoryRepository.save(new CategoryEntity(new CategoryDto("Education", "category-education")));
            categoryRepository.save(new CategoryEntity(new CategoryDto("Others", "category-others")));
        }
    }
}
