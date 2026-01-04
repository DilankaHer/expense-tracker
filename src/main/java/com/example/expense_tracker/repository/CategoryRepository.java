package com.example.expense_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.expense_tracker.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
