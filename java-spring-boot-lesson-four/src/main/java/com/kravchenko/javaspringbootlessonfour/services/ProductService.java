package com.kravchenko.javaspringbootlessonfour.services;

import com.kravchenko.javaspringbootlessonfour.entities.Product;
import com.kravchenko.javaspringbootlessonfour.repositories.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductDAO productDAO;

    @Autowired
    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> getAllProduct() {
        return productDAO.findAll();
    }

    public Product getProduct(Long id) {
        return productDAO.findById(id);
    }

    public void remove(Long id) {
        productDAO.remove(id);
    }

    public void add(Product product) {
        productDAO.add(product);
    }

    public void update(Product product) {
        productDAO.update(product);
    }
}
