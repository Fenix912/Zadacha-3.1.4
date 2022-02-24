package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Component
public class DbInit implements CommandLineRunner {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) {
        Role role1 = new Role("ROLE_USER");
        Role role2 = new Role("ROLE_ADMIN");
        roleService.save(role1);
        roleService.save(role2);
        List<Role> u1roles = new ArrayList<>();
        List<Role> u2roles = new ArrayList<>();
        u1roles.add(role1);
        u1roles.add(role2);
        u2roles.add(role1);

        User user1 = new User("admin", "admin", 47, "admin", "admin", u1roles);
        User user2 = new User("user", "user", 47, "user", "user", u2roles);

        userService.save(user1);
        userService.save(user2);

    }
}

