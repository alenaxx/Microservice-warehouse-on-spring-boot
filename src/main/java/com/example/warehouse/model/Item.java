package com.example.warehouse.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Item {
    private final UUID id;
    private final String name;
    private final float price;
    private int actualAmount;
    private int availableAmount;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getActualAmount() {
        return actualAmount;
    }

    public int getAvailableAmount() {
        return availableAmount;
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
