package ru.sprng.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.sprng.demo.entities.Item;
import ru.sprng.demo.entities.ItemProjection;
import ru.sprng.demo.repositories.ItemRepository;

import java.util.List;

@Service
public class ItemsService {
    private ItemRepository itemRepository;

    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

//    public List<Item> getAllItems(){
//        System.out.println(itemRepository.findByTitle("Tree"));
//        System.out.println(itemRepository.findByCostBetween(30, 70));
//        return itemRepository.findAll();
//    }
      public Page<Item> getItemsWithPagingAndFiltering(Specification<Item> specifications, Pageable pageable){
        return itemRepository.findAll(specifications, pageable);
}

}
