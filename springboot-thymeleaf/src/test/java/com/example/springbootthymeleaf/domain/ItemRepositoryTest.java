package com.example.springbootthymeleaf.domain;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Item 리포지토리 테스트")
class ItemRepositoryTest {

    private final ItemRepository itemRepository = new ItemRepository();

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
        Item saveItem = itemRepository.save(item);

        // when
        ItemDto itemDto = new ItemDto("소설책", new Money(100), 2);
        itemRepository.updateItem(saveItem.getId(), itemDto);

        // then
        Item updatedItem = itemRepository.findById(saveItem.getId());

        assertThat(updatedItem.getItemName()).isEqualTo("소설책");
        assertThat(updatedItem.getPrice().getValue()).isEqualTo(100);
        assertThat(updatedItem.getQuantity()).isEqualTo(2);
    }

    @Test
    void findAll() {
        // given
        Item item = new Item("만화책", new Money(10), 1);
        Item item2 = new Item("만화책", new Money(10), 2);
        List<Item> givenItems = List.of(item, item2);
        itemRepository.save(item);
        itemRepository.save(item2);

        // when
        List<Item> items = itemRepository.findAll();

        // then
        assertThat(items).hasSize(2);
        assertThat(items).containsAll(givenItems);
    }

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }
}