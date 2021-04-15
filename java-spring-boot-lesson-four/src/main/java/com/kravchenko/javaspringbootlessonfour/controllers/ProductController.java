package com.kravchenko.javaspringbootlessonfour.controllers;

import com.kravchenko.javaspringbootlessonfour.entities.Product;
import com.kravchenko.javaspringbootlessonfour.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String indexPage(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "product_views/index";
    }

    @GetMapping("/{id}")
    public String editProduct(@PathVariable(value = "id") Long id,
                              Model model) {
        model.addAttribute("product", productRepository.findById(id));
        return "product_views/product_form";
    }

    @PostMapping("/product_update")
    public String updateProduct(Product product) {
        productRepository.update(product);
        return "redirect:/product";
    }

    @GetMapping("/new")
    public String newProduct(Model model) {
        Product product = new Product(0L, "", "", new BigDecimal("0"));
        productRepository.add(product);
        model.addAttribute("product", product);
        return "product_views/product_form";
    }

    @GetMapping("/delete/{id}")
    public String removeProduct(@PathVariable(value = "id") Long id) {
        productRepository.remove(id);
        return "redirect:/product";
    }
}