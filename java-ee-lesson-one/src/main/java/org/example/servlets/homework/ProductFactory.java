package org.example.servlets.homework;

public class ProductFactory {
    public Product createProduct (int id, String title, float cost) {
        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setCost(cost);
        return product;
    }
}
