package com.example.warehouse.service;

import com.example.warehouse.dao.ItemDao;
import com.example.warehouse.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WarehouseService {

    private final ItemDao itemDao;

    @Autowired
    public WarehouseService(@Qualifier("postgres") ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public int addItem(Item item) {
        return itemDao.addItem(item);
    }

    public List<Item> getAllItems() {
        return itemDao.getAllItems();
    }

    public Optional<Item> getItemById(UUID id) {
        return itemDao.getItemById(id);
    }

}
