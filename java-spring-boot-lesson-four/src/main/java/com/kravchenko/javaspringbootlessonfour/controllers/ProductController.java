package com.kravchenko.javaspringbootlessonfour.controllers;

import com.kravchenko.javaspringbootlessonfour.entities.Product;
import com.kravchenko.javaspringbootlessonfour.services.ProductService;
import com.kravchenko.javaspringbootlessonfour.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String indexPage(Model model,
                            @RequestParam(name = "titleFilter", required = false) Optional<String> titleFilter,
                            @RequestParam(name = "min", required = false) Optional<BigDecimal> min,
                            @RequestParam(name = "max", required = false) Optional<BigDecimal> max,
                            @RequestParam(name = "page", required = false) Optional<Integer> page,
                            @RequestParam(name = "size", required = false) Optional<Integer> size,
                            @RequestParam(name = "sort_field", required = false) Optional<String> sortField,
                            @RequestParam(name = "sort_dir", required = false) Optional<String> sortDir) {

        model.addAttribute("products", productService.getByParams(titleFilter, min, max, page, size, sortField, sortDir));
        if (titleFilter.isPresent()) model.addAttribute("titleFilter", titleFilter.get());
        if (min.isPresent()) model.addAttribute("min", min.get());
        if (max.isPresent()) model.addAttribute("max", max.get());
        if (page.isPresent()) model.addAttribute("page", page.get());
        if (size.isPresent()) model.addAttribute("size", size.get());
        if (sortField.isPresent()) model.addAttribute("sort_field", sortField.get());
        if (sortDir.isPresent()) model.addAttribute("sort-dir", sortDir.get());
        return "product_views/index";
    }

    @GetMapping("/{id}")
    public String editProduct(@PathVariable(value = "id") Long id,
                              Model model) {
        model.addAttribute("product", productService.getById(id).orElseThrow(NotFoundException::new));
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

    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException exception) {
        ModelAndView modelAndView = new ModelAndView("product_views/not_found");
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }
}