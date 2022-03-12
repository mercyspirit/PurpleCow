package com.purplecow.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import com.purplecow.demo.model.Item;

public interface ItemRepository extends MongoRepository<Item, String> {
    Item findItemById(String id);

    List<Item> findItemByName(String name);

}
