package com.example.warehouse.dao;

import com.example.warehouse.dto.ItemAmountDto;
import com.example.warehouse.model.Item;

import java.util.List;
import java.util.UUID;

public interface WarehouseDao {

    Item addItem(UUID id, Item item);

    default Item addItem(Item item) {
        UUID id = UUID.randomUUID();
        return addItem(id, item);
    }

    List<Item> getAllItems();

    Item getItemById(UUID id);

    Item updateItemAmount(UUID id, ItemAmountDto itemAmountDto);

}
