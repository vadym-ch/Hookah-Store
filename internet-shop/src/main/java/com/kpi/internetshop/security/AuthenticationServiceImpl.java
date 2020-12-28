package com.kpi.internetshop.security;

import com.kpi.internetshop.entity.User;
import com.kpi.internetshop.service.RoleService;
import com.kpi.internetshop.service.ShoppingCartService;
import com.kpi.internetshop.service.UserService;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private RoleService roleService;
    private UserService userService;
    private ShoppingCartService shoppingCartService;

    public AuthenticationServiceImpl(RoleService roleService, UserService userService,
            ShoppingCartService shoppingCartService) {
        this.roleService = roleService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public User register(String firstName, String lastName, String email, String password) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(Set.of(roleService.getRoleByName("USER")));
        user = userService.create(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}
