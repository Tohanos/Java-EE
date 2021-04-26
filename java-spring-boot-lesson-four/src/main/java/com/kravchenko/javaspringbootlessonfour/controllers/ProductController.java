package com.kravchenko.javaspringbootlessonfour.controllers;

import com.kravchenko.javaspringbootlessonfour.entities.Product;
import com.kravchenko.javaspringbootlessonfour.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String indexPage(Model model, @RequestParam(name = "titleFilter", required = false) String titleFilter,
                            @RequestParam(name = "minFilter", required = false) BigDecimal minFilter,
                            @RequestParam(name = "maxFilter", required = false) BigDecimal maxFilter) {
        int currentPage = 1;
        int maxProductsPerPage = 5;
        List<Product> products = productService.getAllProduct();
        List<Product> productsByTitle;
        if (titleFilter == null || titleFilter.isBlank()) {
            productsByTitle = products;
        } else {
            productsByTitle = productService.getByTitle(titleFilter);
        }

        BigDecimal maxPrice = new BigDecimal(0);
        for (Product product : products) {
            if (maxPrice.compareTo(product.getPrice()) == -1) {
                maxPrice = product.getPrice();
            }
        }

        if (minFilter == null) {
            minFilter = new BigDecimal(0);
        }
        if (maxFilter == null) {
            maxFilter = maxPrice;
        }
        List<Product> productsByMinMax = productService.getByMinMaxCriteria(minFilter, maxFilter);

        List<Product> selectedProducts = productsByTitle.stream()
                .distinct()
                .filter(productsByMinMax::contains)
                .collect(Collectors.toList());

        model.addAttribute("products", selectedProducts);
        return "product_views/index";
    }

    @GetMapping("/{id}")
    public String editProduct(@PathVariable(value = "id") Long id,
                              Model model) {
        model.addAttribute("product", productService.getById(id));
        return "product_views/product_form";
    }

    @PostMapping("/product_update")
    public String updateProduct(Product product) {
        productService.addOrUpdate(product);
        return "redirect:/product";
    }

    @GetMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute(new Product());
        return "product_views/product_form";
    }

    @GetMapping("/delete/{id}")
    public String removeProduct(@PathVariable(value = "id") Long id) {
        productService.remove(id);
        return "redirect:/product";
    }
}