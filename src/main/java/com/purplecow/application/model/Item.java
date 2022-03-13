package com.purplecow.application.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 * Author: ziying.qiu@gmail.com
 */

@Document(collection = "items")
public class Item {
    @Id
    private String id;
    private String name;

    public Item(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Item: { id: " + id + ", name: " + name + " }";
    }
}
