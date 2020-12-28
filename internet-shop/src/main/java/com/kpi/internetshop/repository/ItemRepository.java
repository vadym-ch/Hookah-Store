package com.kpi.internetshop.repository;

import com.kpi.internetshop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Item getByProductIdAndUserId (Long productId, Long userId);
}
