package com.example.warehouse.dao;

import com.example.warehouse.model.Item;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ItemDao {

    int addItem(UUID id, Item item);

    default int addItem(Item item) {
        UUID id = UUID.randomUUID();
        return addItem(id, item);
    }

    List<Item> getAllItems();

    Optional<Item> getItemById(UUID id);


}
