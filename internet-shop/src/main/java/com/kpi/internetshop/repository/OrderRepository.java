package com.kpi.internetshop.repository;

import com.kpi.internetshop.entity.Order;
import com.kpi.internetshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> getByUser(User user);
}
