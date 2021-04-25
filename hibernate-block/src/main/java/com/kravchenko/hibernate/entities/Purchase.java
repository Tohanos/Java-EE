package com.kravchenko.hibernate.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "purchases", schema = "products")
public class Purchase {
    @Id
    @GeneratedValue
    @Column(name = "id")
    Long id;

    @Column(name = "customer_id")
    Long customer_id;

    @Column(name = "product_id")
    Long product_id;

    @Column(name = "price")
    BigDecimal price;

    public Purchase() {
    }

    public Long getId() {
        return id;
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Purchases{" +
                "id=" + id +
                ", customer_id=" + customer_id +
                ", product_id=" + product_id +
                ", price=" + price +
                '}';
    }
}
