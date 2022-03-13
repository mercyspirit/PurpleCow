package com.purplecow.application.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import com.purplecow.application.model.Item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class ItemRepositoryTests {
    @MockBean
    private ItemRepository itemRepository;
    private Item item1;
    private Item item2;
    private Item item3;
    private Item editedItem3;

    private final String id1 = "1";
    private final String id2 = "2";
    private final String id3 = "3";
    private final String name1 = "Purple Cow";
    private final String name2 = "Green Cow";
    private final String name3 = "Red";
    private final String editedName3 = "Red Cow";

    private final String invalidId = "10";
    private final String nameSearch = "cow";

    private final List<Item> items = new ArrayList<>();
    private final List<Item> editedItems = new ArrayList<>();
    private final List<Item> filteredItems = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        item1 = new Item(id1, name1);
        item2 = new Item(id2, name2);
        item3 = new Item(id3, name3);
        editedItem3 = new Item(id3, editedName3);
        items.add(item1);
        items.add(item2);
        items.add(item3);

        filteredItems.add(item1);
        filteredItems.add(item2);

        editedItems.add(item1);
        editedItems.add(item2);
        editedItems.add(editedItem3);

        Mockito.when(itemRepository.findAll()).thenReturn(items);

        Mockito.when(itemRepository.findItemById(id1)).thenReturn(item1);

        Mockito.when(itemRepository.findItemByName(nameSearch)).thenReturn(filteredItems);

        Mockito.when(itemRepository.saveAll(editedItems)).thenReturn(editedItems);

        Mockito.when(itemRepository.save(editedItem3)).thenReturn(editedItem3);

    }

    @Test
    public void testFindAll_thenItemListShouldBeReturned() {
        List<Item> foundItems = itemRepository.findAll();

        assertNotNull(foundItems);
        assertEquals(3, foundItems.size());
    }

    @Test
    public void testFindItemById_thenItemShouldBeReturned() {
        Item foundItem = itemRepository.findItemById(id1);

        assertNotNull(foundItem);
        assertEquals(foundItem.getName(), name1);
    }

    @Test
    public void testFindItemByInvalidId_thenItemShouldNotBeReturned() {
        Item foundItem = itemRepository.findItemById(invalidId);

        assertNull(foundItem);
    }

    @Test
    public void testFindItemByName_thenItemsShouldBeReturned() {
        List<Item> foundItems = itemRepository.findItemByName(nameSearch);

        assertNotNull(foundItems);
        assertEquals(2, foundItems.size());
    }

    @Test
    public void testSaveOrUpdateItemList_thenItemsShouldBeUpdated() {
        List<Item> foundItems = itemRepository.saveAll(editedItems);

        assertNotNull(foundItems);
        assertEquals(3, foundItems.size());
        assertEquals(editedItems.get(2).getName(), editedName3);
    }

    @Test
    public void testSaveOrUpdateItem_thenItemShouldBeUpdated() {
        Item foundItem = itemRepository.save(editedItem3);
        assertNotNull(foundItem);
        assertEquals(editedItem3.getName(), foundItem.getName());
    }

    @Test
    public void testDeleteItemById() {
        itemRepository.deleteById(item1.getId());

        Mockito.verify(itemRepository, Mockito.times(1))
                .deleteById(item1.getId());
    }

    @Test
    public void testDeleteAllItems() {
        itemRepository.deleteAll();

        Mockito.verify(itemRepository, Mockito.times(1))
                .deleteAll();
    }
}
