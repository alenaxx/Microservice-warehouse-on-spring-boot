package com.example.warehouse.dao;

import com.example.warehouse.model.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class RealDaoService implements ItemDao {
    private final JdbcTemplate jdbcTemplate;

   private static List<Item> DB = new ArrayList<>();
    @Autowired
    public RealDaoService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addItem(UUID id, Item item) {
        jdbcTemplate.update(
                "INSERT INTO item ( id,name, price,actualamount,availableamount) VALUES (?, ?, ?, ?, ?)",
              id,   item.getName(),item.getPrice(),item.getActualAmount(),item.getAvailableAmount()
        );
        return 0;
    }

    @Override
    public List<Item> getAllItems() {
        final String sql ="SELECT id, name, price,actualamount,availableamount FROM item";
        return jdbcTemplate.query(sql,(resultSet ,i)-> {
             UUID id =UUID.fromString(resultSet.getString("id"));
             String name = resultSet.getString("name");
             float price = resultSet.getFloat("price");
             int actualamount=  resultSet.getInt("actualamount");
             int availableamount = resultSet.getInt("availableamount");
             return new Item (id, name, price, actualamount, availableamount);
         });

    }

    @Override
    public Optional<Item> getItemById(UUID id) {
        final String sql ="SELECT id,name, price,actualamount,availableamount FROM item WHERE id =?";
        Item item= jdbcTemplate.queryForObject(sql,new Object[]{id},(resultSet ,i)-> {
            UUID itemId =UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            float price = resultSet.getFloat("price");
            int actualamount=  resultSet.getInt("actualamount");
            int availableamount = resultSet.getInt("availableamount");
            return new Item (itemId, name, price, actualamount, availableamount);
        });
        return Optional.ofNullable(item);
    }



}
