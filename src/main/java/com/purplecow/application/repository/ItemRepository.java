package com.purplecow.application.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import com.purplecow.application.model.Item;

/*
 * Author: ziying.qiu@gmail.com
 */

public interface ItemRepository extends MongoRepository<Item, String> {
    Item findItemById(String id);

    List<Item> findItemByName(String name);

}
