package com.example.warehouse.dto;



import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class OrderItemDto {
    @JsonProperty("itemId")
    public UUID getItemId() {
        return itemId;
    }

    public int getAmount() {
        return amount;
    }

    private final UUID itemId;
    private final UUID orderId;
    private final int amount;

    public OrderItemDto(@JsonProperty("itemId") UUID itemId, @JsonProperty("orderId")UUID orderId, @JsonProperty("amount") int amount) {
        this.itemId = itemId;
        this.orderId = orderId;
        this.amount = amount;
    }
}