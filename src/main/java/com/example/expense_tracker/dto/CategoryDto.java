package com.example.expense_tracker.dto;

public class CategoryDto {
    private String name;
    private String icon;
    private String catType;

    public CategoryDto(String name, String icon, String catType) {
        this.name = name;
        this.icon = icon;
        this.catType = catType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCatType() {
        return catType;
    }

    public void setCatType(String catType) {
        this.catType = catType;
    }
}
