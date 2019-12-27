package com.example.warehouse.dto;

public class ItemAmountDto {
    private String amountType;
    private int amount;

    public String getAmountType() {
        return amountType;
    }

    public void setAmountType(String amountType) {
        this.amountType = amountType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ItemAmountDto(String amountType, int amount) {
        this.amountType = amountType;
        this.amount = amount;
    }
}
