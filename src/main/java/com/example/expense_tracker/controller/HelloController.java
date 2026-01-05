package com.example.expense_tracker.controller;

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
import com.example.expense_tracker.entity.TransactionEntity;
import com.example.expense_tracker.service.CategoryService;
import com.example.expense_tracker.service.TransactionService;

@Controller
public class HelloController {
    private final TransactionService transactionService;
    private final CategoryService categoryService;

    public HelloController(TransactionService transactionService, CategoryService categoryService) {
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
            List<TransactionEntity> transactions = this.transactionService.getAllTransactions("date");
            if (transactions.isEmpty()) {
                model.addAttribute("transactionsByDate", null);
            } else {
                Map<String, List<TransactionEntity>> transactionsByDate = transactions.stream()
                    .collect(Collectors.groupingBy(TransactionEntity::getDateString));
                model.addAttribute("transactionsByDate", transactionsByDate);
            }
            return "transactions";
        }
        return "home";
    }

    @GetMapping("/transactionForm")
    public String transactionForm(Model model) {
        // List<CategoryEntity> categories = this.categoryService.getAllCategories();
        // System.out.println(categories);
        // model.addAttribute("categories", categories);
        return "transaction_form";
    }

    @PostMapping("/addTransaction")
    public String addTransaction(@ModelAttribute TransactionDto transactionDto, Model model) {
        this.transactionService.addTransaction(transactionDto);
        List<TransactionEntity> transactions = this.transactionService.getAllTransactions("date");
        Map<String, List<TransactionEntity>> transactionsByDate = transactions.stream()
            .collect(Collectors.groupingBy(TransactionEntity::getDateString));
        model.addAttribute("transactionsByDate", transactionsByDate);
        return "fragments/transaction_list :: transactionsList";
    }

    @GetMapping("/categories")
    public String categories(Model model) {
        List<CategoryEntity> categories = this.categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "fragments/selection_menu :: categorySelection";
    }
}
