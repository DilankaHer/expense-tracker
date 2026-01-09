package com.example.expense_tracker.controller;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.expense_tracker.entity.CategoryEntity;
import com.example.expense_tracker.entity.TransactionEntity;
import com.example.expense_tracker.service.CategoryService;
import com.example.expense_tracker.service.TransactionService;

@Controller
public class MainController {
    private final TransactionService transactionService;
    private final CategoryService categoryService;

    public MainController(TransactionService transactionService, CategoryService categoryService) {
        this.transactionService = transactionService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String hello(Model model) {
        return "index";
    }

    @GetMapping("/tab")
    public String home(@RequestParam String tab, Model model) {
        if (tab.equals("transactions")) {
            YearMonth yearMonth = YearMonth.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue());
            List<TransactionEntity> transactions = this.transactionService.getAllTransactions("date", yearMonth);
            if (transactions.isEmpty()) {
                model.addAttribute("transactionsByDate", null);
            } else {
                Map<String, List<TransactionEntity>> transactionsByDate = transactions.stream()
                    .collect(Collectors.groupingBy(TransactionEntity::getDateString, LinkedHashMap::new, Collectors.toList()));
                model.addAttribute("transactionsByDate", transactionsByDate);
            }
            return "transactions";
        }
        return "home";
    }

    @GetMapping("/categories")
    public String categories(Model model) {
        List<CategoryEntity> categories = this.categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "fragments/selection_menu :: categorySelection";
    }

    @GetMapping("/subCategories")
    public String subCategories(Model model) {
        List<CategoryEntity> subCategories = this.categoryService.getAllSubCategories();
        model.addAttribute("subCategories", subCategories);
        return "fragments/selection_menu :: subCategorySelection";
    }
}
