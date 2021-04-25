package com.kravchenko.hibernate.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "purchases", schema = "public")
public class Purchase {
    @Id
    @GeneratedValue
    @Column(name = "id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    @Column(name = "price")
    BigDecimal price;

    public Purchase() {
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Product getProduct() {
        return product;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Purchases{" +
                "id=" + id +
                ", customer=" + customer +
                ", product=" + product +
                ", price=" + price +
                '}';
    }
}
