package com.example.springbootthymeleaf.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new ConcurrentHashMap<>();
    private static final AtomicLong sequence = new AtomicLong(0L);

    public Item save(Item item) {
        long id = sequence.incrementAndGet();
        item.setId(id);
        store.put(id, item);
        return item;
    }

    public Item findById(Long id) {
        if(!store.containsKey(id)){
            throw new RuntimeException("아이템이 존재하지 않습니다");
        }
        return store.get(id);
    }

    public void updateItem(Long itemId, ItemDto itemDto) {
        Item item = store.get(itemId);
        item.setItemName(itemDto.getItemName());
        item.setPrice(itemDto.getPrice());
        item.setQuantity(itemDto.getQuantity());
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
