package com.example.expense_tracker.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.expense_tracker.dto.BudgetDto;
import com.example.expense_tracker.entity.BudgetEntity;
import com.example.expense_tracker.entity.TransactionEntity;
import com.example.expense_tracker.repository.BudgetRepository;

@Service
public class BudgetService {
    private final BudgetRepository budgetRepository;
    private final TransactionService transactionService;

    public BudgetService(BudgetRepository budgetRepository, TransactionService transactionService) {
        this.transactionService = transactionService;
        this.budgetRepository = budgetRepository;
    }

    @Transactional
    public void addBudget(BudgetEntity budgetEntity) {
        this.budgetRepository.save(budgetEntity);
    }

    @Transactional(readOnly = true)
    public List<BudgetEntity> getAllBudgets() {
        return this.budgetRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<BudgetEntity> getBudgetByYearAndMonth(Integer year, Integer month) {
        return this.budgetRepository.findByYearAndMonth(year, month);
    }

    @Transactional
    public void updateBudget(BudgetEntity budgetEntity) {
        BudgetEntity budget = budgetRepository.findById(budgetEntity.getId())
            .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("Budget not found"));

        budget.setYear(budgetEntity.getYear());
        budget.setMonth(budgetEntity.getMonth());
        budget.setAmount(budgetEntity.getAmount());
    }

    @Transactional
    public void deleteBudget(Long id) {
        this.budgetRepository.deleteById(id);
    }

    public BudgetDto createBudgetDto(int year, int month) {
        Optional<BudgetEntity> budgetEntity = getBudgetByYearAndMonth(year, month);

        String path = "/addBudget";
        if (budgetEntity.isEmpty()) {
            return new BudgetDto(null, path, year, month, 0, 0, "N/A", "N/A", "N/A");
        }
        List<TransactionEntity> transactions = this.transactionService.getAllTransactions(null, YearMonth.of(year, month));
        Long id = budgetEntity.get().getId();
        double amount = budgetEntity.get().getAmount();
        double expenses = transactions.stream().mapToDouble(TransactionEntity::getAmount).sum();
        double remaining = amount - expenses;
        double percentage = Math.round((expenses / amount) * 100 * 100.0) / 100.0;
        double dailySpend = 0;
        path = "/updateBudget";

        if (month == LocalDate.now().getMonthValue()) {
            dailySpend = Math.round(remaining / (LocalDate.now().lengthOfMonth() - LocalDate.now().getDayOfMonth()) * 100.0) / 100.0;
        }
        return new BudgetDto(id, path, year, month, amount, expenses, String.valueOf(remaining), dailySpend == 0 ? "N/A" : String.valueOf(dailySpend), String.valueOf(percentage));
    }
}
