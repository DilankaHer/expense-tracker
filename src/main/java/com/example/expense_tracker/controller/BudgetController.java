package com.example.expense_tracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.expense_tracker.entity.BudgetEntity;
import com.example.expense_tracker.service.BudgetService;

@Controller
public class BudgetController {
    private final BudgetService budgetService;
    
    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping("/budget")
    public String budgets(Model model, @RequestParam Integer month, @RequestParam Integer year) {
        model.addAttribute("budget", this.budgetService.createBudgetDto(year, month));
        return "fragments/budget_display :: budgetDisplay";
    }

    @PostMapping("/addBudget")
    public String addBudget(@ModelAttribute BudgetEntity budgetEntity, Model model) {
        this.budgetService.addBudget(budgetEntity);
        model.addAttribute("budget", this.budgetService.createBudgetDto(budgetEntity.getYear(), budgetEntity.getMonth()));
        return "fragments/budget_display :: budgetDisplay";
    }

    @PostMapping("/updateBudget")
    public String updateBudget(@ModelAttribute BudgetEntity budgetEntity, Model model) {
        this.budgetService.updateBudget(budgetEntity);
        model.addAttribute("budget", this.budgetService.createBudgetDto(budgetEntity.getYear(), budgetEntity.getMonth()));
        return "fragments/budget_display :: budgetDisplay";
    }
}
