package ru.sprng.demo.repositories.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.sprng.demo.entities.Item;

public class ItemsSpecs {
    public static Specification<Item> titleContains(String word){ //title LIKE 'apple%'
        return (Specification<Item>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("title"), "%" + word + "%");
    }
    public static Specification<Item> costGreaterThanOrEq(int value) {
        return (Specification<Item>) (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), value);
        };
    }
}
