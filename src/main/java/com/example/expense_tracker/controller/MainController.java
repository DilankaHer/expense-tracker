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

import com.example.expense_tracker.dto.BudgetDto;
import com.example.expense_tracker.entity.CategoryEntity;
import com.example.expense_tracker.entity.SubCategoryEntity;
import com.example.expense_tracker.entity.TransactionEntity;
import com.example.expense_tracker.service.BudgetService;
import com.example.expense_tracker.service.CategoryService;
import com.example.expense_tracker.service.SubCategoryService;
import com.example.expense_tracker.service.TransactionService;

@Controller
public class MainController {
    private final TransactionService transactionService;
    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;
    private final BudgetService budgetService;

    public MainController(TransactionService transactionService, CategoryService categoryService, SubCategoryService subCategoryService, BudgetService budgetService) {
        this.transactionService = transactionService;
        this.categoryService = categoryService;
        this.subCategoryService = subCategoryService;
        this.budgetService = budgetService;
    }

    @GetMapping("/")
    public String hello(Model model) {
        model.addAttribute("path", "/budget");
        model.addAttribute("target", "#budgetDisplay");
        model.addAttribute("budget", this.budgetService.createBudgetDto(LocalDate.now().getYear(), LocalDate.now().getMonthValue()));
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
                System.out.println("Innnn");
                System.out.println(transactionsByDate);
                model.addAttribute("transactionsByDate", transactionsByDate);
            }
            model.addAttribute("path", "/transactions");
            model.addAttribute("target", "#transactionsList");
            return "transactions";
        }
        model.addAttribute("path", "/budget");
        model.addAttribute("target", "#budgetDisplay");
        model.addAttribute("budget", this.budgetService.createBudgetDto(LocalDate.now().getYear(), LocalDate.now().getMonthValue()));
        return "home";
    }

    @GetMapping("/changeMonth")
    public String changeMonth(@RequestParam String tab, @RequestParam Integer month, @RequestParam Integer year, Model model) {
        model.addAttribute("tab", tab);
        if (tab.equals("transactions")) {
            Map<String, List<TransactionEntity>> transactionsByDate = getTransactionsByDate(YearMonth.of(year, month));
            model.addAttribute("transactionsByDate", transactionsByDate);
            return "fragments/month_year_oob :: transactionsOob";
        }
        model.addAttribute("budget", this.budgetService.createBudgetDto(year, month));
        return "fragments/month_year_oob :: budgetOob";
    }

    @GetMapping("/categories")
    public String categories(Model model) {
        List<CategoryEntity> categories = this.categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "fragments/selection_menu :: categorySelection";
    }

    @GetMapping("/subCategories")
    public String subCategories(@RequestParam String category, Model model) {
        List<SubCategoryEntity> subCategories = this.subCategoryService.getSubCategoriesByCategoryName(category);
        model.addAttribute("subCategories", subCategories);
        return "fragments/selection_menu :: subCategorySelection";
    }

    @GetMapping("/category") 
    public String category(Model model, @RequestParam String name, @RequestParam String icon) {
        CategoryEntity category = new CategoryEntity(name, icon);
        model.addAttribute("category", category);
        return "fragments/selection_menu :: categorySelectionSingle";
    }

    private Map<String, List<TransactionEntity>> getTransactionsByDate(YearMonth yearMonth) {
        List<TransactionEntity> transactions = this.transactionService.getAllTransactions("date", yearMonth);
        return transactions.stream()
            .collect(Collectors.groupingBy(TransactionEntity::getDateString, LinkedHashMap::new, Collectors.toList()));
    }
}
