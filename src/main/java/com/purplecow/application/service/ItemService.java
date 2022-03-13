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

    Item saveOrUpdateItem(Item item);

    List<Item> saveOrUpdateItemList(List<Item> item);

    void deleteItemById(String id);

    void deleteAllItems();
}
