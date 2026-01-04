package com.example.expense_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.expense_tracker.entity.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
