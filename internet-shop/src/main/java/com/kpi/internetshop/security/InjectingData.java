package com.kpi.internetshop.security;

import com.kpi.internetshop.entity.*;
import com.kpi.internetshop.repository.OrderRepository;
import com.kpi.internetshop.service.*;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class InjectingData {
    private RoleService roleService;
    private UserService userService;
    private ProductService productService;
    private OrderRepository orderRepository;
    private AuthenticationService authenticationService;
    private ShoppingCartService shoppingCartService;

    public InjectingData(RoleService roleService, UserService userService, ProductService productService,
            OrderRepository orderRepository, AuthenticationService authenticationService,
            ShoppingCartService shoppingCartService) {
        this.roleService = roleService;
        this.userService = userService;
        this.productService = productService;
        this.orderRepository = orderRepository;
        this.authenticationService = authenticationService;
        this.shoppingCartService = shoppingCartService;
    }

    @PostConstruct
    public void init() {
        Role userRole = Role.of("USER");
        roleService.add(userRole);
        Role adminRole = Role.of("ADMIN");
        roleService.add(adminRole);

        User admin = new User();
        admin.setEmail("admin@gmail.com");
        admin.setPassword("1");
        admin.setRoles(Set.of(adminRole));
        userService.create(admin);

        User user = new User();
        user.setFirstName("Barbara");
        user.setLastName("Palvin");
        user.setEmail("barbara@gmail.com");
        user.setPassword("1");
        authenticationService.register(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());

        User user1 = new User();
        user1.setFirstName("Max");
        user1.setLastName("Steel");
        user1.setEmail("max@gmail.com");
        user1.setPassword("1");
        authenticationService.register(user1.getFirstName(), user1.getLastName(), user1.getEmail(),
                user1.getPassword());

        Product product1 = new Product();
        product1.setName("Hookah Yahya Mono");
        product1.setDescription("strong mouthpiece");
        product1.setAmount(25);
        product1.setPrice(2999);
        product1.setImageFileName("foto1.jpg");

        Product product2 = new Product();
        product2.setName("Hookah Yahya Elegance");
        product2.setDescription("brightful color");
        product2.setPrice(2799.99);
        product2.setAmount(10);
        product2.setImageFileName("foto2.jpg");

        Product product3 = new Product();
        product3.setName("Hookah Yahya Coilover");
        product3.setDescription("solid glass");
        product3.setPrice(2899);
        product3.setAmount(15);
        product3.setImageFileName("foto3.jpg");

        Product product4 = new Product();
        product4.setName("Hookah Smoke Box");
        product4.setDescription("small size");
        product4.setPrice(1500);
        product4.setAmount(20);
        product4.setImageFileName("foto4.jpg");

        Product product5 = new Product();
        product5.setName("Hookah Solo");
        product5.setDescription("comfy holder");
        product5.setPrice(1600);
        product5.setAmount(7);
        product5.setImageFileName("foto5.jpg");

        Product product6 = new Product();
        product6.setName("Hookah AK-47");
        product6.setDescription("gold-colored");
        product6.setPrice(3300);
        product6.setAmount(5);
        product6.setImageFileName("foto6.jpg");

        Product product7 = new Product();
        product7.setName("Hookah Lex AM");
        product7.setDescription("made of silumin");
        product7.setPrice(2800);
        product7.setAmount(22);
        product7.setImageFileName("foto7.jpg");

        Product product8 = new Product();
        product8.setName("Hookah Amy Deluxe");
        product8.setDescription("ceramic");
        product8.setPrice(4000);
        product8.setAmount(8);
        product8.setImageFileName("foto8.jpg");

        productService.create(product1);
        productService.create(product2);
        productService.create(product3);
        productService.create(product4);
        productService.create(product5);
        productService.create(product6);
        productService.create(product7);
        productService.create(product8);
    }
}
