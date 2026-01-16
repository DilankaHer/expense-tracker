package com.example.expense_tracker.dto;

public class BudgetDto {
    private Long id;
    private String path;
    private int year;
    private int month;
    private double amount;
    private double expenses;
    private String remaining;
    private String dailySpend;
    private String percentage;

    public BudgetDto(Long id, String path, int year, int month, double amount, double expenses, String remaining, String dailySpend, String percentage) {
        this.id = id;
        this.path = path;
        this.year = year;
        this.month = month;
        this.amount = amount;
        this.expenses = expenses;
        this.remaining = remaining;
        this.dailySpend = dailySpend;
        this.percentage = percentage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    public String getRemaining() {
        return remaining;
    }

    public void setRemaining(String remaining) {
        this.remaining = remaining;
    }

    public String getDailySpend() {
        return dailySpend;
    }

    public void setDailySpend(String dailySpend) {
        this.dailySpend = dailySpend;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }
}
