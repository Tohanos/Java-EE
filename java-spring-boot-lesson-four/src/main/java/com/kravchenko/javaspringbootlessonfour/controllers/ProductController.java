package com.kravchenko.javaspringbootlessonfour.controllers;

import com.kravchenko.javaspringbootlessonfour.entities.Product;
import com.kravchenko.javaspringbootlessonfour.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String indexPage(Model model, @RequestParam(name = "titleFilter", required = false) String titleFilter,
                            @RequestParam(name = "minFilter", required = false) BigDecimal minFilter,
                            @RequestParam(name = "maxFilter", required = false) BigDecimal maxFilter,
                            @RequestParam(name = "page") Optional<Integer> page,
                            @RequestParam(name = "size") Optional<Integer> size) {
        int currentPage = page.orElse(0);
        model.addAttribute("currentPage", currentPage);
        int pageSize = size.orElse(5);
        model.addAttribute("pageSize", pageSize);

        List<Product> products = productService.getAllProduct();
        List<Product> productsByTitle;

        //выбор продукта поиском по названию
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
        //выбор продукта по критерию минимума и максимума стоимости
        List<Product> productsByMinMax = productService.getByMinMaxCriteria(minFilter, maxFilter);

        //поиск пересечения списков выбранных продуктов
        List<Product> selectedProducts = productsByTitle.stream()
                .distinct()
                .filter(productsByMinMax::contains)
                .collect(Collectors.toList());

        //попытка сделать паджинацию
        int startProduct = currentPage * pageSize;
        List<Product> productList;

        if (selectedProducts.size() < startProduct) {
            productList = Collections.emptyList();
        } else {
            productList = selectedProducts
                    .subList(startProduct, Math.min(startProduct + pageSize, selectedProducts.size()));
        }

        int totalPages = selectedProducts.size() / pageSize + 1;
        model.addAttribute("totalPages", totalPages);

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("products", productList);
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