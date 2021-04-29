package com.kravchenko.javaspringbootlessonfour.repositories.specifications;

import com.kravchenko.javaspringbootlessonfour.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecification {
    public static Specification<Product> trueLiteral() {
        return (root, query, builder) -> builder.isTrue(builder.literal(true));
    }

    public static Specification<Product> titleLike(String titleFilter) {
        return (root, query, builder) -> builder.like(root.get("title"), "%" + titleFilter + "%");
    }

    public static Specification<Product> minMaxSelection(BigDecimal minFilter, BigDecimal maxFilter) {
        return ((root, query, builder) -> builder.between(root.get("price"), minFilter, maxFilter));
    }

}
