package com.kpi.internetshop.repository;

import com.kpi.internetshop.entity.ShoppingCart;
import com.kpi.internetshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    ShoppingCart getByUser_Email(String email);

    void deleteByUser(User user);
}
