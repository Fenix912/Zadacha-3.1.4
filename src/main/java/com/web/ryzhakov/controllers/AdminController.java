package com.web.ryzhakov.controllers;

import com.web.ryzhakov.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.web.ryzhakov.service.RoleService;
import com.web.ryzhakov.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String adminPage(Model model, Authentication authentication) {
        User user1 = (User) authentication.getPrincipal();
        model.addAttribute("MainUser", user1);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.allRoles());
        model.addAttribute("newUser", new User());
        return "admin/index";
    }
}


