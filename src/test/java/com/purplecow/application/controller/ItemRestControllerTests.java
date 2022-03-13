package com.purplecow.application.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.purplecow.application.model.Item;
import com.purplecow.application.service.ItemService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ItemRestControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ItemService itemService;

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
    }

    @Test
    public void givenItems_whenGetAllItems_thenReturnJsonArray() throws Exception {
        given(itemService.findAll()).willReturn(items);

        mvc.perform(get("/items/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name", is(item1.getName())));
    }

    @Test
    public void givenItems_whenGetNoItems_thenReturnJsonArray() throws Exception {
        given(itemService.findAll()).willReturn(new ArrayList<Item>());

        mvc.perform(get("/items/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void givenItems_whenGetAllItemsFail_thenReturnException() throws Exception {

        given(itemService.findAll()).willThrow(IllegalStateException.class);

        mvc.perform(get("/items/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void givenItems_whenGetAllItemsWithQuery_thenReturnJsonArray() throws Exception {
        given(itemService.findItemByName(nameSearch)).willReturn(editedItems);

        mvc.perform(get("/items?query=cow")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].name", is(editedItem3.getName())));
    }

    @Test
    public void givenItems_whenGetAllItemsWithQueryFail_thenReturnException() throws Exception {

        given(itemService.findItemByName(nameSearch)).willThrow(IllegalStateException.class);

        mvc.perform(get("/items?query=cow")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void givenItems_whenGetNoItemsWithQuery_thenReturnException() throws Exception {

        given(itemService.findItemByName(nameSearch)).willReturn(new ArrayList<Item>());

        mvc.perform(get("/items?query=cow")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void givenItem_whenGetItemById_thenReturnJson() throws Exception {
        given(itemService.findItemById(item1.getId())).willReturn(item1);

        mvc.perform(get("/items/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(item1.getId())))
                .andExpect(jsonPath("$.name", is(item1.getName())));
    }

    @Test
    public void givenItem_whenGetEmptyItemById_thenReturnJson() throws Exception {
        given(itemService.findItemById(invalidId)).willReturn(null);

        mvc.perform(get("/items/10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void givenItem_whenGetItemByIdFail_thenReturnException() throws Exception {
        given(itemService.findItemById(invalidId)).willThrow(IllegalStateException.class);

        mvc.perform(get("/items/10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void givenItem_whenSaveItem_thenReturnJson() throws Exception {
        when(
                itemService.saveOrUpdateItem(
                        (Item) notNull()))
                .thenReturn(item1);
        ObjectMapper mapper = new ObjectMapper();

        mvc.perform(put("/items/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(item1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(item1.getId())))
                .andExpect(jsonPath("$.name", is(item1.getName())));
    }

    @Test
    public void givenItem_whenSaveItemFail_thenReturnException() throws Exception {
        when(
                itemService.saveOrUpdateItem(
                        (Item) notNull()))
                .thenThrow(IllegalStateException.class);
        ObjectMapper mapper = new ObjectMapper();

        mvc.perform(put("/items/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(item1)))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void givenItem_whenSaveItemList_thenReturnJsonArray() throws Exception {
        when(
                itemService.saveOrUpdateItemList(
                        (List<Item>) notNull()))
                .thenReturn(items);

        ObjectMapper mapper = new ObjectMapper();

        mvc.perform(post("/items/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(items)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(item1.getId())))
                .andExpect(jsonPath("$[0].name", is(item1.getName())));
    }

    @Test
    public void givenItem_whenSaveItemListFail_thenReturnException() throws Exception {
        when(
                itemService.saveOrUpdateItemList(
                        (List<Item>) notNull()))
                .thenThrow(IllegalStateException.class);

        ObjectMapper mapper = new ObjectMapper();

        mvc.perform(post("/items/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(items)))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void givenItem_whenDeleteAll_thenReturn() throws Exception {

        mvc.perform(delete("/items/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(itemService, Mockito.times(1))
                .deleteAllItems();
    }

    @Test
    public void givenItem_whenDeleteAllFail_thenReturnException() throws Exception {
        doThrow(new IllegalStateException()).when(itemService).deleteAllItems();

        mvc.perform(delete("/items/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());

        Mockito.verify(itemService, Mockito.times(1))
                .deleteAllItems();
    }

    @Test
    public void givenItem_whenDeleteByIdFail_thenReturnException() throws Exception {
        doThrow(new IllegalStateException()).when(itemService).deleteItemById(id1);
        mvc.perform(delete("/items/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
        Mockito.verify(itemService, Mockito.times(1))
                .deleteItemById(id1);
    }

}
