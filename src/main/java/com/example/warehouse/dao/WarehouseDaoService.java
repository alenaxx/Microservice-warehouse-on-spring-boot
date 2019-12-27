package com.example.warehouse.dao;

import com.example.warehouse.dto.ItemAmountDto;
import com.example.warehouse.model.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class WarehouseDaoService implements WarehouseDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WarehouseDaoService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Item addItem(UUID id, Item item) {
        jdbcTemplate.update(
                "INSERT INTO item ( id,name, price,actualamount,availableamount) VALUES (?, ?, ?, ?, ?)",
                id, item.getName(), item.getPrice(), item.getActualAmount(), item.getAvailableAmount()
        );
        return null;
    }

    @Override
    public List<Item> getAllItems() {
        final String sql = "SELECT id, name, price,actualamount,availableamount FROM item";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            float price = resultSet.getFloat("price");
            int actualamount = resultSet.getInt("actualAmount");
            int availableamount = resultSet.getInt("availAbleamount");
            return new Item(id, name, price, actualamount, availableamount);
        });

    }

    @Override
    public Item getItemById(UUID id) {
        final String sql = "SELECT id,name, price,actualamount,availableamount FROM item WHERE id =?";
        Item item = jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) -> {
            UUID itemId = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            float price = resultSet.getFloat("price");
            int actualamount = resultSet.getInt("actualAmount");
            int availableamount = resultSet.getInt("availableAmount");
            return new Item(itemId, name, price, actualamount, availableamount);
        });
        return item;
    }

    @Override
    public Item updateItemAmount(UUID id, ItemAmountDto itemAmountDto) {
        Item item = getItemById(id);
        if (item == null) {
            return null;
        }
        String sqlActual = "UPDATE item SET actualAmount = ? WHERE id = ?";
        String sqlAvailable = "UPDATE item SET availableAmount = ? WHERE id = ?";
        if (itemAmountDto.getAmountType().equals("actual")) {
            item.setActualAmount(item.getActualAmount() + itemAmountDto.getAmount());
            jdbcTemplate.update(sqlActual,
                    item.getActualAmount(), item.getId());
        }
        else if (itemAmountDto.getAmountType().equals("available")) {
            item.setActualAmount(item.getActualAmount() + itemAmountDto.getAmount());
            jdbcTemplate.update(sqlAvailable,
                    item.getAvailableAmount(), item.getId());
        }else {
            return null;
        }


return item;
}
}
