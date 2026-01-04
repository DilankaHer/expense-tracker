package com.example.expense_tracker.migration;

import com.example.expense_tracker.dto.CategoryDto;
import com.example.expense_tracker.entity.CategoryEntity;
import com.example.expense_tracker.repository.CategoryRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public DataInitializer(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            categoryRepository.save(new CategoryEntity(new CategoryDto("Food", "category-food")));
            categoryRepository.save(new CategoryEntity(new CategoryDto("Transport", "category-transport")));
            categoryRepository.save(new CategoryEntity(new CategoryDto("Bill", "category-bill")));
            categoryRepository.save(new CategoryEntity(new CategoryDto("Entertainment", "category-bill")));
        }
    }
}
