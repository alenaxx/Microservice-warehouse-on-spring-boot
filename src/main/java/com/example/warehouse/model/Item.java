package com.example.warehouse.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Item {
    private final UUID id;
    private  String name;
    private  float price;
    private int actualAmount;
    private int availableAmount;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(int actualAmount) {
        this.actualAmount = actualAmount;
    }

    public int getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(int availableAmount) {
        this.availableAmount = availableAmount;
    }


    public Item(@JsonProperty("id") UUID id,
                @JsonProperty("name") String name,
                @JsonProperty("price") float price,
                @JsonProperty("actualAmount") int actualAmount,
                @JsonProperty("availableAmount") int availableAmount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.actualAmount = actualAmount;
        this.availableAmount = availableAmount;
    }



}
