package com.example.expense_tracker.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.expense_tracker.dto.TransactionDto;
import com.example.expense_tracker.entity.TransactionEntity;
import com.example.expense_tracker.repository.TransactionRepository;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public void addTransaction(TransactionDto transactionDto) {
        TransactionEntity entity = new TransactionEntity(transactionDto);
        this.transactionRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public List<TransactionEntity> getAllTransactions() {
        return this.transactionRepository.findAll();
    }
}
