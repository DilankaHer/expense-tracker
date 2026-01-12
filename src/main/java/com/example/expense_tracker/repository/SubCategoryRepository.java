package com.example.expense_tracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.expense_tracker.entity.SubCategoryEntity;

public interface SubCategoryRepository extends JpaRepository<SubCategoryEntity, Long> {
    List<SubCategoryEntity> findByCategoryName(String categoryName);

    SubCategoryEntity findByName(String name);

    SubCategoryEntity findByNameAndCategoryName(String name, String categoryName);
}
