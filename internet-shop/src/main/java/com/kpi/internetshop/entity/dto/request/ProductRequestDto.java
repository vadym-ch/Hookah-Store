package com.kpi.internetshop.entity.dto.request;

public class ProductRequestDto {
    private String name;
    private String description;
    private double price;
    private int amount;
    private String imageFileName;

    public ProductRequestDto(String name, String description, double price, int amount, String imageFileName) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
        this.imageFileName = imageFileName;
    }

    public ProductRequestDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }
}
