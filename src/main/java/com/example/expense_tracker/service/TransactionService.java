package com.example.expense_tracker.service;

import java.time.YearMonth;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

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
    public List<TransactionEntity> getAllTransactions(String sortBy, @RequestParam(required = false) YearMonth yearMonth) {
        if (yearMonth != null) {
            return this.transactionRepository.findAllByDateGreaterThanEqualAndDateLessThanEqual(yearMonth.atDay(1), yearMonth.atEndOfMonth(), Sort.by(Sort.Direction.DESC, sortBy));
        }
        if (sortBy == null) {
            return this.transactionRepository.findAll();
        }
        return this.transactionRepository.findAll(Sort.by(Sort.Direction.DESC, sortBy));
    }
}
