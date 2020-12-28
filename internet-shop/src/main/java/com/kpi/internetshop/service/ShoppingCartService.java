package com.kpi.internetshop.service;

import com.kpi.internetshop.entity.Item;
import com.kpi.internetshop.entity.ShoppingCart;
import com.kpi.internetshop.entity.User;

public interface ShoppingCartService extends GenericService<ShoppingCart, Long> {
    void registerNewShoppingCart(User user);

    ShoppingCart getByUserEmail(String email);

    void clear(ShoppingCart shoppingCart);

    void addItem(Item item, ShoppingCart shoppingCart);

    void deleteItem(Item item, ShoppingCart shoppingCart);

    double getSumOfProducts(ShoppingCart shoppingCart);
}
