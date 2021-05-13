package com.kravchenko.javaspringbootlessonfour.services;

import com.kravchenko.javaspringbootlessonfour.entities.Product;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CartService {

    private final HashMap<Product, Integer> cart = new HashMap<>();

    public void addProduct(Product product) {
        if (cart.containsKey(product)) {
            int numbers = cart.get(product);
            cart.replace(product, numbers++);
        } else {
            cart.put(product, 1);
        }
    }

    public void removeProduct(Product product) {
        if (cart.containsKey(product)) {
            cart.remove(product);
        }
    }

    public List<Product> getProductList() {
        return List.copyOf(cart.keySet());
    }
}
