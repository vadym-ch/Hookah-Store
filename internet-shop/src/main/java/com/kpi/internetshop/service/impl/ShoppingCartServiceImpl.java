package com.kpi.internetshop.service.impl;

import com.kpi.internetshop.entity.Item;
import com.kpi.internetshop.entity.Product;
import com.kpi.internetshop.entity.ShoppingCart;
import com.kpi.internetshop.entity.User;
import com.kpi.internetshop.repository.ShoppingCartRepository;
import com.kpi.internetshop.service.ItemService;
import com.kpi.internetshop.service.ProductService;
import com.kpi.internetshop.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private ShoppingCartRepository shoppingCartRepository;
    private ItemService itemService;
    private ProductService productService;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, ItemService itemService, ProductService productService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.itemService = itemService;
        this.productService = productService;
    }

    @Override
    public void registerNewShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart getByUserEmail(String email) {
        return shoppingCartRepository.getByUser_Email(email);
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        List<Item> items = shoppingCart.getItems();
        shoppingCart.setItems(new ArrayList<>());
        update(shoppingCart);
        for(Item item : items) {
            Product product = productService.get(item.getProductId());
            product.setAmount(product.getAmount() + item.getAmount());
            productService.update(product);
            itemService.delete(item.getId());
        }
    }

    @Override
    public void addItem(Item item, ShoppingCart shoppingCart) {
        Long userId = shoppingCart.getUser().getId();
        List<Item> items = shoppingCart.getItems();
        if(items.stream().filter(it -> it.getProductId() == item.getProductId() && item.getUserId() == userId).count() == 0) {
             items.add(item);
       } else {
            items.stream()
                   .filter(it -> it.getProductId() == item.getProductId() && item.getUserId() == userId)
                   .forEach(it -> it.setAmount(it.getAmount() + 1));
        }
       shoppingCart.setItems(items);
       update(shoppingCart);
    }

    @Override
    public void deleteItem(Item item, ShoppingCart shoppingCart) {
        List<Item> items = shoppingCart.getItems();
        items.remove(item);
        shoppingCart.setItems(items);
        update(shoppingCart);
        Product product = productService.get(item.getProductId());
        product.setAmount(product.getAmount() + item.getAmount());
        productService.update(product);
        itemService.delete(item.getId());
    }

    @Override
    public double getSumOfProducts(ShoppingCart shoppingCart) {
        return shoppingCart.getItems().stream().mapToDouble(product -> product.getPrice() * product.getAmount()).sum();
    }

    @Override
    public ShoppingCart get(Long id) {
        return shoppingCartRepository.findById(id).get();
    }

    @Override
    public List<ShoppingCart> getAll() {
        return shoppingCartRepository.findAll();
    }

    @Override
    public ShoppingCart update(ShoppingCart item) {
        ShoppingCart shoppingCart = get(item.getId());
        shoppingCart.setItems(item.getItems());
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public void delete(Long id) {
        shoppingCartRepository.deleteById(id);
    }
}
