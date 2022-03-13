package com.purplecow.application.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ItemTests {

    private Item item1;
    private Item item2;
    private Item item3;

    private final String id1 = "1";
    private final String id2 = "2";
    private final String id3 = "3";
    private final String name1 = "Purple Cow";
    private final String name2 = "Green Cow";
    private final String name3 = "Red";
    private final String editedName3 = "Red Cow";
    private final String stringOutput = "Item: { id: " + id3 + ", name: " + name3 + " }";

    @BeforeEach
    public void setUp() {
        item1 = new Item(id1, name1);
        item2 = new Item(id2, name2);
        item3 = new Item(id3, name3);
    }

    @Test
    public void testGetId() {
        assertEquals(item1.getId(), id1);
    }

    @Test
    public void testGetName() {
        assertEquals(item2.getId(), id2);
    }

    @Test
    public void testSetId() {
        item1.setId(id2);
        assertEquals(item1.getId(), id2);
    }

    @Test
    public void testSetName() {
        item3.setName(editedName3);
        assertEquals(item3.getName(), editedName3);
    }

    @Test
    public void testToString() {
        assertEquals(stringOutput, item3.toString());
    }
}
