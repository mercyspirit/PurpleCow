package com.purplecow.demo.service;

import java.util.List;

import com.purplecow.demo.model.Item;

public interface ItemService {
    List<Item> findAll();

    Item findItemById(String id);

    List<Item> findItemByName(String name);

    Item saveOrUpdateItem(Item item);

    void deleteItemById(String id);
}
