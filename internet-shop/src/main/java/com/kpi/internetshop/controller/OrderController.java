package com.kpi.internetshop.controller;

import com.kpi.internetshop.entity.Item;
import com.kpi.internetshop.entity.Order;
import com.kpi.internetshop.entity.ShoppingCart;
import com.kpi.internetshop.service.ItemService;
import com.kpi.internetshop.service.OrderService;
import com.kpi.internetshop.service.ShoppingCartService;
import com.kpi.internetshop.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class OrderController {
    private OrderService orderService;
    private ShoppingCartService shoppingCartService;
    private UserService userService;
    private ItemService itemService;

    public OrderController(OrderService orderService, ShoppingCartService shoppingCartService, UserService userService,
            ItemService itemService) {
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.itemService = itemService;
    }

    @GetMapping("/orders")
    public String getAll(Model model) {
        List<Order> orders = orderService.getAll();
        model.addAttribute("orderList", orders);
        return "allOrders";
    }

    @GetMapping("/orders/delete/{id}")
    public String deleteProduct(@PathVariable(value = "id") Long id) {
        Order order = orderService.get(id);
        orderService.delete(id);
        order.getItems().stream().forEach(item -> itemService.delete(item.getId()));
        return "redirect:/orders";
    }

    @GetMapping("/orders/details/{id}")
    public String showDetails(@PathVariable(value = "id") Long id, Model model) {
        Order order = orderService.get(id);
        List<Item> items = order.getItems();
        model.addAttribute("items", items);
        return "order_details";
    }

    @GetMapping("/order/complete")
    public String completeOrder(Authentication authentication) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        ShoppingCart cart = shoppingCartService.getByUserEmail(principal.getUsername());
        orderService.complete(cart);
        return "order_success";
    }

    @GetMapping("/orders/show")
    public String showOrderList(Authentication authentication, Model model) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        List<Order> orders = orderService.getByUser(userService.getByEmail(principal.getUsername()));
        if (orders.isEmpty()) {
            return "userOrders_empty";
        }
        model.addAttribute("orders", orders);
        return "userOrders";
    }

    @GetMapping("/user/orders/details/{id}")
    public String showOrderDetails(@PathVariable Long id, Model model) {
        Order order = orderService.get(id);
        model.addAttribute("items", order.getItems());
        return "userOrder_details";
    }
}
