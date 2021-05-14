package com.kravchenko.javaspringbootlessonfour.services;

import com.kravchenko.javaspringbootlessonfour.entities.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Vector;

@Service
public class CartService {

    private List<Product> cart = new Vector<>();

    public void addProduct(Product product) {
        if (cart.stream()
                .map((r) -> r.getId())
                .filter(product.getId()::equals)
                .count() == 0) {
            cart.add(product);
        }
    }

    public void removeProduct(Product product) {
        cart.removeIf(product1 -> product1.getId() == product.getId());
    }

    public List<Product> getProductList() {
        return List.copyOf(cart);
    }
}
