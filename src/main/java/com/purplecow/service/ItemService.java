package com.purplecow.service;

import com.purplecow.model.Item;

import java.util.List;

public interface ItemService {
    List<Item> findAll();

    Item findByItemId(String id);

    Item findByItemQuery(String query);

    List<Item> findAllByOrderIdAscen();

    Item saveOrUpdateItem(Item item);

    void deleteItemById(String id);
}
