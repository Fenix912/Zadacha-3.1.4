package com.web.ryzhakov.DbInit;

import com.web.ryzhakov.models.Role;
import com.web.ryzhakov.models.User;
import com.web.ryzhakov.service.UserService;
import org.springframework.stereotype.Component;
import com.web.ryzhakov.service.RoleService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class postConstr {

    private final UserService userService;
    private final RoleService roleService;

    public postConstr(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void firstInitDB() {

        roleService.saveRole(new Role("ADMIN"));
        roleService.saveRole(new Role("USER"));

        Set<Role> roleAdmin = new HashSet<>();
        roleAdmin.add(roleService.getRoleByName("ADMIN"));
        Set<Role> roleUser = new HashSet<>();
        roleUser.add(roleService.getRoleByName("USER"));

        User admin = new User();
        admin.setName("admin");
        admin.setSurname("admin");
        admin.setEmail("admin@mail.ru");
        admin.setAge(47);
        admin.setPassword("admin");
        admin.setRoles(roleAdmin);
        userService.addUser(admin);

        User user = new User();
        user.setName("user");
        user.setSurname("user");
        user.setEmail("user@mail.ru");
        user.setAge(47);
        user.setPassword("user");
        user.setRoles(roleUser);
        userService.addUser(user);

    }
}