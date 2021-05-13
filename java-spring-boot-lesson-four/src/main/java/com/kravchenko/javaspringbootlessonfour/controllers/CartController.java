package com.kravchenko.javaspringbootlessonfour.controllers;

import com.kravchenko.javaspringbootlessonfour.entities.Product;
import com.kravchenko.javaspringbootlessonfour.services.CartService;
import com.kravchenko.javaspringbootlessonfour.services.ProductService;
import com.kravchenko.javaspringbootlessonfour.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String indexPage(Model model) {
        model.addAttribute("products",
                cartService.getProductList());
        return "cart_views/cart";
    }

    @GetMapping("/del/{id}")
    public String removeProduct(@PathVariable(value = "id") Long id) {
        cartService.removeProduct(productService.getById(id).get());
        return "redirect:/cart";
    }



    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException exception) {
        ModelAndView modelAndView = new ModelAndView("product_views/not_found");
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }

}
