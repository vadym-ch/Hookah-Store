package com.kpi.internetshop.service;

import com.kpi.internetshop.entity.Item;

public interface ItemService extends GenericService<Item, Long> {
     Item create(Item item);
     Item getByProductAndUser(Long productId, Long userId);
}
