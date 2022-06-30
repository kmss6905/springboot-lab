package com.example.springbootthymeleaf.web.basic;

import com.example.springbootthymeleaf.domain.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String editForm(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String editItem(@PathVariable Long itemId, ItemReqDto itemReqDto) {
        ItemDto itemDto = new ItemDto(itemReqDto.getItemName(), new Money(itemReqDto.getPrice()), itemReqDto.getQuantity());
        itemRepository.updateItem(itemId, itemDto);

        // status : 302
        // Response Location Header : https://localhost:8080/basic/items/1
        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    @PostMapping("/add")
    public String save(ItemReqDto itemReqDto, RedirectAttributes redirectAttributes, Model model) {
        Item item = new Item(itemReqDto.getItemName(), new Money(itemReqDto.getPrice()), itemReqDto.getQuantity());
        Item savedItem = itemRepository.save(item);

        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        model.addAttribute("item", savedItem);

        // PRG, Post/Redirect/GET
        // url 에 변수를 더해서 사용하는 것은 URL 인코딩이 안되기 때문에 위험하다.
        return "redirect:/basic/items/{itemId}";
    }

    @PostConstruct
    public void init() {

        itemRepository.save(new Item("itemA", new Money(1000), 10));
        itemRepository.save(new Item("itemB", new Money(2000), 20));
    }
}
