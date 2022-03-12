package com.purplecow.demo.service.impl;

import com.purplecow.demo.model.Item;
import com.purplecow.demo.repository.ItemRepository;
import com.purplecow.demo.service.ItemService;

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
    public void deleteItemById(String id) {
        itemRepository.deleteById(id);
    }

}
