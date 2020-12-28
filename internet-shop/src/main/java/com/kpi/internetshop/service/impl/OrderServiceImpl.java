package com.kpi.internetshop.service.impl;

import com.kpi.internetshop.entity.Item;
import com.kpi.internetshop.entity.Order;
import com.kpi.internetshop.entity.ShoppingCart;
import com.kpi.internetshop.entity.User;
import com.kpi.internetshop.repository.OrderRepository;
import com.kpi.internetshop.service.ItemService;
import com.kpi.internetshop.service.OrderService;
import com.kpi.internetshop.service.ProductService;
import com.kpi.internetshop.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private ShoppingCartService shoppingCartService;
    private ItemService itemService;
    private ProductService productService;


    public OrderServiceImpl(OrderRepository orderRepository, ShoppingCartService shoppingCartService, ItemService itemService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.shoppingCartService = shoppingCartService;
        this.itemService = itemService;
        this.productService = productService;
    }

    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order complete(ShoppingCart shoppingCart) {
        List<Item> items = shoppingCart.getItems();
        Order order = new Order();
        order.setUser(shoppingCart.getUser());
        order.setOrderDate(LocalDateTime.now());
        shoppingCartService.clear(shoppingCart);
        items.stream().forEach(item -> itemService.create(item));
        List<Item> itemsForOrder = new ArrayList<>();
        for (Item item : items) {
            Item it = itemService.getByProductAndUser(item.getProductId(), item.getUserId());
            itemsForOrder.add(it);
        }
        order.setItems(itemsForOrder);
        order.setSum(order.getItems().stream().mapToDouble(item -> item.getAmount()* item.getPrice()).sum());
        Order newOrder = create(order);
        order.getItems().stream().forEach(item -> productService.deleteAmount(productService.get(item.getProductId()), item.getAmount()));
        return newOrder;
    }

    @Override
    public List<Order> getByUser(User user) {
        return orderRepository.getByUser(user);
    }

    @Override
    public Order get(Long id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order update(Order item) {
        return null;
    }

    //  @Override
  //  public Order update(Order item) {
      //  Order order = get(item.getId());
      //  order.setUser(item.getUser());
      //  order.setOrderDate(item.getOrderDate());
       // order.setProducts(item.getProducts());
      //  return orderRepository.save(order);
  //  }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
