package com.kravchenko.hibernate.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products", schema = "public")
public class Product {
    @Id
    @GeneratedValue
    @Column(name = "id")
    Long id;

    @Column(name = "title")
    String title;

    @Column(name = "price")
    BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    Manufacturer manufacturer;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
    List<Purchase> purchaseList;

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public List<Purchase> getPurchaseList() {
        return purchaseList;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", manufacturer=" + manufacturer +
                '}';
    }
}