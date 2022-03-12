package com.purplecow.application.service.impl;

import com.purplecow.application.model.Item;
import com.purplecow.application.repository.ItemRepository;
import com.purplecow.application.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public Item findItemById(String id) {
        return itemRepository.findItemById(id);
    }

    @Override
    public List<Item> findItemByName(String name) {
        return itemRepository.findItemByName(name);
    }

    @Override
    public Item saveOrUpdateItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public List<Item> saveOrUpdateItemList(List<Item> itemList) {
        return itemRepository.saveAll(itemList);
    }

    @Override
    public void deleteItemById(String id) {
        itemRepository.deleteById(id);
    }

    @Override
    public void deleteAllItems() {
        itemRepository.deleteAll();
    }

}
