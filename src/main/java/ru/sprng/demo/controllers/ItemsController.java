package ru.sprng.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sprng.demo.entities.Item;
import ru.sprng.demo.repositories.specification.ItemsSpecs;
import ru.sprng.demo.services.ItemsService;

import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemsController {
    private ItemsService itemsService;

    @Autowired
    public void setItemsService(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

//    @GetMapping
//    public String showItemsPage(Model model){
//        model.addAttribute("items", itemsService.getAllItems() );
//        System.out.println();
//        return "items";
//    }

     @GetMapping
     public String showItemsPage(Model model){
         Specification<Item> filter = Specification.where(null);
         filter = filter.and(ItemsSpecs.costGreaterThanOrEq(30));
         List<Item> resultList = itemsService.getItemsWithPagingAndFiltering(filter, PageRequest.of(2,2)).getContent();
         model.addAttribute("items", resultList);
         return "items";
    }
}
