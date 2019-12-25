package com.example.warehouse.dto;


import com.example.warehouse.model.Order;

import java.util.ArrayList;

public class OrderDto extends Order {

    public ArrayList<OrderItemDto> getItems() {
        return items;
    }

    private ArrayList<OrderItemDto> items;

    public OrderDto() {
        super();
    }

    public OrderDto(Order order, ArrayList<OrderItemDto> items){
        super(order.getId(), order.getTotalAmount(), order.getOrderStatus().ordinal(), String.valueOf(order.totalCost));
        this.items = items;
    }
}
