package com.purplecow.repository;

import com.purplecow.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ItemRepository extends MongoRepository<Item, String> {
    Item findByItemId(String id);

    List<Item> findByItemQuery(String query);

    List<Item> findAllByOrderIdAscen();
}
