package com.example.expense_tracker.controller;

import java.time.YearMonth;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    private Map<String, List<TransactionEntity>> getTransactionsByDate(YearMonth yearMonth) {
        List<TransactionEntity> transactions = this.transactionService.getAllTransactions("date", yearMonth);
        return transactions.stream()
            .collect(Collectors.groupingBy(TransactionEntity::getDateString, LinkedHashMap::new, Collectors.toList()));
    }
}
