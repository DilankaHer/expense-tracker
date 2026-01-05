package com.example.expense_tracker.dto;

import java.sql.Date;

public class TransactionDto {
    private String category;
    private String subCategory;
    private double amount;
    private Date date;
    private String description;
    private String icon;

    public TransactionDto(String category, String subCategory, double amount, Date date, String description, String icon) {
        this.category = category;
        this.subCategory = subCategory;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.icon = icon;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
