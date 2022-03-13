package com.purplecow.application.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import com.purplecow.application.model.Item;

/*
 * Author: ziying.qiu@gmail.com
 */

public interface ItemRepository extends MongoRepository<Item, String> {
    Item findItemById(String id);

    @Query("{'name': {'$regex' : ?0, '$options' : 'i'}}")
    List<Item> findItemByName(String name);

}
