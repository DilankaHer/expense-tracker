package com.example.expense_tracker.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.expense_tracker.dto.TransactionDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
    private String subCategory;
    private double amount;
    private String description;
    private Date date;
    private String dateString;
    private String icon;

    public TransactionEntity() {
    }

    public TransactionEntity(TransactionDto transactionDto) {
        this.category = transactionDto.getCategory();
        this.subCategory = transactionDto.getSubCategory();
        this.amount = transactionDto.getAmount();
        this.description = transactionDto.getDescription();
        this.date = transactionDto.getDate();
        this.dateString = makeDateString(transactionDto.getDate());
        this.icon = transactionDto.getIcon();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String makeDateString(Date date) {
        LocalDate localDate = date.toLocalDate();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy");
        String formattedDate = localDate.format(myFormatObj);
        return formattedDate;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getIcon() {
        return icon;
    }   

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
