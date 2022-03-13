package com.purplecow.application.service;

import java.util.List;

import com.purplecow.application.model.Item;

/*
 * Author: ziying.qiu@gmail.com
 */

public interface ItemService {
    List<Item> findAll();

    Item findItemById(String id);

    List<Item> findItemByName(String name);

    List<Item> saveOrUpdateItemList(List<Item> item);

    Item saveOrUpdateItem(Item item);

    void deleteItemById(String id);

    void deleteAllItems();
}
