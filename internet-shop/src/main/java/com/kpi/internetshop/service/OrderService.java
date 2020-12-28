package com.kpi.internetshop.service;

import com.kpi.internetshop.entity.Order;
import com.kpi.internetshop.entity.ShoppingCart;
import com.kpi.internetshop.entity.User;

import java.util.List;

public interface OrderService extends GenericService<Order, Long> {
    Order create(Order order);

    Order complete(ShoppingCart shoppingCart);

    List<Order> getByUser(User user);
}
