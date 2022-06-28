package com.example.springbootthymeleaf.web.basic;

import com.example.springbootthymeleaf.domain.Item;
import com.example.springbootthymeleaf.domain.ItemRepository;
import com.example.springbootthymeleaf.domain.ItemReqDto;
import com.example.springbootthymeleaf.domain.Money;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final static Logger log = LoggerFactory.getLogger(BasicItemController.class);
    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/{itemId}/edit")
    public String edit(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute("item") ItemReqDto itemReqDto) {
        Item item = new Item();
        item.setItemName(itemReqDto.getItemName());
        item.setQuantity(itemReqDto.getQuantity());
        item.setPrice(new Money(itemReqDto.getPrice()));
        itemRepository.save(item);
        return "basic/addForm";
    }

    @PostConstruct
    public void init() {

        itemRepository.save(new Item("itemA", new Money(1000), 10));
        itemRepository.save(new Item("itemB", new Money(2000), 20));
    }
}
