package com.example.warehouse.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class OrderItemDto {
    @JsonProperty("item_id")
    public UUID getItemId() {
        return itemId;
    }

    public int getAmount() {
        return amount;
    }

    private final UUID itemId;
    private final int amount;

    public OrderItemDto(@JsonProperty("item_id") UUID itemId, @JsonProperty("amount") int amount) {
        this.itemId = itemId;
        this.amount = amount;
    }
}