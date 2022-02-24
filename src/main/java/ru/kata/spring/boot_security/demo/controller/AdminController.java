package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserDetailsServiceImp;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;



@Controller
public class AdminController {

    private UserService userService;
    private RoleService roleService;

    private final UserDetailsServiceImp userDetailsServiceImp;

    @Autowired
    public AdminController(UserDetailsServiceImp userDetailsServiceImp) {
        this.userDetailsServiceImp = userDetailsServiceImp;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/admin/users")
    public String getUsers(Model model, Principal principal) {
        model.addAttribute("keyValue", userService.getDemandedUsers());
        model.addAttribute("user1", userDetailsServiceImp.findByUsername(principal.getName()));
        model.addAttribute("user", new User());
        model.addAttribute("rolesList", roleService.getDemandedRoles());

        return "usersView";
    }

    @PostMapping("/admin/users")
    public String create(@ModelAttribute("user") User user,
                         @RequestParam(value = "rolesIdArr", required = false) int[] rolesIdArr) {
        User updatedUser = userService.setRolesToUser(user, rolesIdArr);
        userService.save(updatedUser);

        return "redirect:/admin/users/";
    }

    @PatchMapping("/admin/users/edit")
    public String update(@ModelAttribute("user") User user,
                         @RequestParam(value = "rolesIdArr", required = false) int[] rolesIdArr) {
        User updatedUser = userService.setRolesToUser(user, rolesIdArr);
        userService.update(updatedUser);
        return "redirect:/admin/users/";
    }

    @DeleteMapping("/admin/users/{id}")
    public String delete(@PathVariable("id") Integer id) {
        userService.delete(id);
        return "redirect:/admin/users/";
    }
}