package com.example.warehouse.dao;

import com.example.warehouse.model.Item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("sqlDao")
public class WarehouseDaoService implements ItemDao {

    private static List<Item> DB = new ArrayList<>();

    @Override
    public int addItem(UUID id, Item item) {
        DB.add(new Item(id, item.getName(), item.getPrice(), item.getActualAmount(), item.getAvailableAmount()));
        return 1;
    }

    @Override
    public List<Item> getAllItems() {
        return DB;
    }

    @Override
    public Optional<Item> getItemById(UUID id) {
        return DB.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst();
    }



}
