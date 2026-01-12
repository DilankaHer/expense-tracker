package com.example.expense_tracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.expense_tracker.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity findByName(String name);

    @Query("""
        SELECT c FROM CategoryEntity c
        ORDER BY 
            CASE WHEN LOWER(c.name) = 'others' THEN 1 ELSE 0 END,
            c.name
    """)
    List<CategoryEntity> findAllOrderByOthersLast();
}
