package ru.sprng.demo.repositories.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.sprng.demo.entities.Item;
import ru.sprng.demo.entities.Product;

import java.math.BigDecimal;

public class ProductsSpecs {
    public static Specification<Product> titleContains(String word){ //title LIKE 'apple%'
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("title"), "%" + word + "%");
    }
    public static Specification<Product> priceGreaterThanOrEq(BigDecimal value) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), value);
        };
    }
    public static Specification<Product> priceLesserThanOrEq(BigDecimal value) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(root.get("price"), value);
        };
    }
}
