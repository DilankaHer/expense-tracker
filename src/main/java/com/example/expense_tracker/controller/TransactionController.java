package com.example.expense_tracker.controller;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.expense_tracker.dto.TransactionDto;
import com.example.expense_tracker.entity.CategoryEntity;
import com.example.expense_tracker.entity.SubCategoryEntity;
import com.example.expense_tracker.entity.TransactionEntity;
import com.example.expense_tracker.service.CategoryService;
import com.example.expense_tracker.service.SubCategoryService;
import com.example.expense_tracker.service.TransactionService;

@Controller
public class TransactionController {
    private final TransactionService transactionService;
    private final SubCategoryService subCategoryService;
    private final CategoryService categoryService;

    public TransactionController(TransactionService transactionService, SubCategoryService subCategoryService, CategoryService categoryService) {
        this.transactionService = transactionService;
        this.subCategoryService = subCategoryService;
        this.categoryService = categoryService;
    }

    @GetMapping("/transactions")
    public String transactions(@RequestParam Integer month, @RequestParam Integer year, Model model) {
        Map<String, List<TransactionEntity>> transactionsByDate = getTransactionsByDate(YearMonth.of(year, month));
        model.addAttribute("transactionsByDate", transactionsByDate);
        return "fragments/transaction_list :: transactionsList";
    }

    @GetMapping("/transactionForm")
    public String transactionForm(@RequestParam Integer month, @RequestParam Integer year, Model model) {
        YearMonth yearMonth = YearMonth.of(year, month);
        model.addAttribute("minDate", yearMonth.atDay(1));
        model.addAttribute("maxDate", yearMonth.atEndOfMonth());
        return "transaction_form";
    }

    @GetMapping("/transactionFormEdit")
    public String transactionFormEdit(@RequestParam Long id, Model model) {
        TransactionEntity transaction = this.transactionService.getTransactionById(id)
            .orElseThrow(() -> new IllegalArgumentException("Transaction not found for id " + id));

        YearMonth yearMonth = YearMonth.from(transaction.getDate().toLocalDate());
        model.addAttribute("minDate", yearMonth.atDay(1));
        model.addAttribute("maxDate", yearMonth.atEndOfMonth());
        model.addAttribute("transaction", transaction);
        return "transaction_form_edit :: transactionFormEdit";
    }

    @PostMapping("/addTransaction")
    public String addTransaction(@ModelAttribute TransactionDto transactionDto, @RequestParam Integer month, @RequestParam Integer year, Model model) {

        CategoryEntity category = categoryService.getCategoryByName(transactionDto.getCategory());

        if (category == null) {
            CategoryEntity categoryEntity = new CategoryEntity(transactionDto.getCategory(), "category-others");
            categoryService.addCategory(categoryEntity);
        }

        if (transactionDto.getSubCategory() != null) {
            SubCategoryEntity subCategory = subCategoryService.getSubCategoryByNameAndCategoryName(transactionDto.getSubCategory(), transactionDto.getCategory());

            if (subCategory == null) {
                SubCategoryEntity subCategoryEntity = new SubCategoryEntity(transactionDto.getSubCategory(), "sub-category-others", categoryService.getCategoryByName(transactionDto.getCategory()));
                subCategoryService.addSubCategory(subCategoryEntity);
            }
        }

        this.transactionService.addTransaction(transactionDto);

        Map<String, List<TransactionEntity>> transactionsByDate = getTransactionsByDate(YearMonth.of(year, month));
        model.addAttribute("transactionsByDate", transactionsByDate);
        return "fragments/transaction_list :: transactionsList";
    }

    @PostMapping("/editTransaction")
    public String editTransaction(@ModelAttribute TransactionEntity transactionEntity, @RequestParam Integer month, @RequestParam Integer year, Model model) {
        CategoryEntity category = categoryService.getCategoryByName(transactionEntity.getCategory());

        if (category == null) {
            CategoryEntity categoryEntity = new CategoryEntity(transactionEntity.getCategory(), transactionEntity.getIcon());
            categoryService.addCategory(categoryEntity);
        }

        if (transactionEntity.getSubCategory() != null && !transactionEntity.getSubCategory().isBlank()) {
            SubCategoryEntity subCategory = subCategoryService.getSubCategoryByNameAndCategoryName(transactionEntity.getSubCategory(), transactionEntity.getCategory());

            if (subCategory == null) {
                SubCategoryEntity subCategoryEntity = new SubCategoryEntity(transactionEntity.getSubCategory(), "sub-category-others", categoryService.getCategoryByName(transactionEntity.getCategory()));
                subCategoryService.addSubCategory(subCategoryEntity);
            }
        }

        this.transactionService.updateTransaction(transactionEntity);

        Map<String, List<TransactionEntity>> transactionsByDate = getTransactionsByDate(YearMonth.of(year, month));
        model.addAttribute("transactionsByDate", transactionsByDate);
        return "fragments/transaction_list :: transactionsList";
    }

    @DeleteMapping("/deleteTransaction")
    public String deleteTransaction(@RequestParam Long id, @RequestParam String date, Model model) {
        LocalDate localDate = LocalDate.parse(date);
        this.transactionService.deleteTransaction(id);
        List<TransactionEntity> transactionsByDate = this.transactionService.getTransactionsByDate(localDate);

        if (!transactionsByDate.isEmpty()) {
            String key = transactionsByDate.get(0).getDateString();
            model.addAttribute("key", key);
            model.addAttribute("value", transactionsByDate);
            return "fragments/transaction_list :: transactionsPerDate";
        }

        List<TransactionEntity> transactions = this.transactionService.getAllTransactions("date", YearMonth.of(localDate.getYear(), localDate.getMonth()));

        if (transactions.isEmpty()) {
            return "fragments/empty :: noTransactions";
        }

        return "fragments/empty :: empty"; 
    }

    private Map<String, List<TransactionEntity>> getTransactionsByDate(YearMonth yearMonth) {
        List<TransactionEntity> transactions = this.transactionService.getAllTransactions("date", yearMonth);
        return transactions.stream()
            .collect(Collectors.groupingBy(TransactionEntity::getDateString, LinkedHashMap::new, Collectors.toList()));
    }
}
