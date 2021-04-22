package com.kravchenko.hibernate.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "purchases", schema = "products")
public class Purchases {
    @Id
    @GeneratedValue
    @Column(name = "id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    Long customer_id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Long product_id;

    @Column(name = "price")
    BigDecimal price;



}
