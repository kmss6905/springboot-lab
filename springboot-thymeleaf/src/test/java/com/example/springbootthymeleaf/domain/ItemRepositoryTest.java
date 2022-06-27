package com.example.springbootthymeleaf.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    private final ItemRepository itemRepository = new ItemRepository();

    @BeforeEach
    void beforeEach() {

    }

    @Test
    void save() {
        // given
        Item item = new Item("만화책", new Money(10), 1);

        // when
        itemRepository.save(item);
        Item findItem = itemRepository.findById(item.getId());

        // then
        assertThat(findItem.getId()).isEqualTo(item.getId());
        assertThat(findItem).isEqualTo(item);
    }

    @Test
    void findById() {
        // given
        Item item = new Item("만화책", new Money(10), 1);
        Item item2 = new Item("만화책", new Money(10), 2);
        itemRepository.save(item);
        itemRepository.save(item2);

        // when
        Item findItem = itemRepository.findById(item.getId());
        Item findItem2 = itemRepository.findById(item2.getId());

        // then
        assertThat(findItem).isEqualTo(item);
        assertThat(findItem2).isEqualTo(item2);


    }

    @Test
    void updateItem() {
        // given
        Item item = new Item("만화책", new Money(10), 1);
        Item item2 = new Item("만화책", new Money(10), 2);
        itemRepository.save(item);
        itemRepository.save(item2);

        // when
        List<Item> items = itemRepository.findAll();
    }

    @Test
    void findAll() {
    }

    @AfterEach
    void afterEach() {

    }
}