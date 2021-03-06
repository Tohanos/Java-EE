package com.kravchenko.hibernate.services;

import com.kravchenko.hibernate.entities.Customer;
import com.kravchenko.hibernate.entities.Product;
import com.kravchenko.hibernate.entities.Purchase;
import com.kravchenko.hibernate.repositories.CustomerDAO;
import com.kravchenko.hibernate.repositories.ProductDAO;
import com.kravchenko.hibernate.repositories.PurchaseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerProductService {

    private CustomerDAO customerDAO;
    private PurchaseDAO purchaseDAO;
    private ProductDAO productDAO;

    @Autowired
    public CustomerProductService() {
        customerDAO = new CustomerDAO();
        purchaseDAO = new PurchaseDAO();
        productDAO = new ProductDAO();
    }

    public List<Product> getProductsByCustomer(Long customerId) {
        Customer customer = customerDAO.findById(customerId);
        List<Purchase> purchases = customer.getPurchases();
        List<Product> products = new ArrayList<>();
        for (Purchase purchase : purchases) {
            products.add(purchase.getProduct());
        }
        return products;
    }

    public List<Customer> getCustomerByProduct(Long productId) {
        Product product = productDAO.findById(productId);
        List<Purchase> purchases = product.getPurchaseList();
        List<Customer> customers = new ArrayList<>();
        for (Purchase purchase : purchases) {
            customers.add(purchase.getCustomer());
        }
        return customers;
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.findAll();
    }

    public Customer getCustomer(Long id) {
        return customerDAO.findById(id);
    }

    public Product getProduct(Long id) {
        return productDAO.findById(id);
    }

}
