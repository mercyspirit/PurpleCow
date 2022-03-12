package com.purplecow.service.impl;

import com.purplecow.model.Item;
import com.purplecow.repository.ItemRepository;
import com.purplecow.service.ItemService;
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
    public Item findByItemId(String id) {
        return itemRepository.findByItemId(id);
    }

    @Override
    public List<Item> findByItemQuery(String query) {
        return itemRepository.findByItemQuery(query);
    }

    @Override
    public List<Item> findAllByOrderIdAscen() {
        return itemRepository.findAllByOrderIdAscen();
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
