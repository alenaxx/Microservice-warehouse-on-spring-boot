package com.example.warehouse.service;

import com.example.warehouse.dao.WarehouseDao;
import com.example.warehouse.dto.ItemAmountDto;
import com.example.warehouse.dto.OrderDto;
import com.example.warehouse.model.Item;
import com.example.warehouse.model.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WarehouseService {

    private final WarehouseDao itemDao;

    @Autowired
    public WarehouseService(@Qualifier("postgres") WarehouseDao itemDao) {
        this.itemDao = itemDao;
    }

    public Item addItem(Item item) {
        return itemDao.addItem(item);
    }

    public List<Item> getAllItems() {
        return itemDao.getAllItems();
    }

    public Item getItemById(UUID id) {
        return itemDao.getItemById(id);
    }

    private Item updateItemAmount(UUID id, ItemAmountDto itemAmountDto) {
        return itemDao.updateItemAmount(id, itemAmountDto);
    }
    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @RabbitListener(queues = "order-warehouse")
    public void orderQueueConsumer(OrderDto orderDto) {
        OrderStatus orderStatus = orderDto.getOrderStatus();
        System.out.println(String.format("Order {%s} status changed to {%s}", orderDto.getId(), orderStatus));
        if (orderStatus == OrderStatus.PAID) {
            orderDto.getItems().forEach(orderItemDto -> {
                this.updateItemAmount(orderItemDto.getItemId(), new ItemAmountDto("available", -orderItemDto.getAmount()));
            });
        } else if (orderStatus == OrderStatus.CANCELLED) {
            orderDto.getItems().forEach(orderItemDto -> {
                this.updateItemAmount(orderItemDto.getItemId(), new ItemAmountDto("available", orderItemDto.getAmount()));
            });
        } else if (orderStatus == OrderStatus.COMPLETED) {
            orderDto.getItems().forEach(orderItemDto -> {
                this.updateItemAmount(orderItemDto.getItemId(), new ItemAmountDto("actual", -orderItemDto.getAmount()));
            });
        }
    }
}
