package com.example.expense_tracker.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.expense_tracker.entity.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    List<TransactionEntity> findAllByDateGreaterThanEqualAndDateLessThanEqual(
        LocalDate start,
        LocalDate end,
        Sort sort
);
}
