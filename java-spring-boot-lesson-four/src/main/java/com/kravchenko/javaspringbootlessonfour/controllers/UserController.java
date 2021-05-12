package com.kravchenko.javaspringbootlessonfour.controllers;

import com.kravchenko.javaspringbootlessonfour.entities.User;
import com.kravchenko.javaspringbootlessonfour.repositories.RoleRepository;
import com.kravchenko.javaspringbootlessonfour.services.UserService;
import com.kravchenko.javaspringbootlessonfour.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private RoleRepository roleRepository;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public String indexUserPage(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user_views/user";
    }

    @GetMapping("/{id}")
    public String editUser(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id).orElseThrow(NotFoundException::new));
        model.addAttribute("roles", roleRepository.findAll());
        return "user_views/user_form";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute(new User());
        return "user_views/user_form";
    }

    @PostMapping("/update")
    public String updateUser(User user) {
        userService.createOrUpdate(user);
        return "redirect:/user";
    }

    @GetMapping("/delete/{id}")
    public String removeUser(@PathVariable(value = "id") Long id) {
        userService.deleteById(id);
        return "redirect:/user";
    }

    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException exception) {
        ModelAndView modelAndView = new ModelAndView("product_views/not_found");
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }
}