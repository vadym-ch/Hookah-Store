package com.kpi.internetshop.service.impl;

import com.kpi.internetshop.entity.Item;
import com.kpi.internetshop.repository.ItemRepository;
import com.kpi.internetshop.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    private ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item get(Long id) {
        return itemRepository.findById(id).get();
    }

    @Override
    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    @Override
    public Item update(Item item) {
        Item temp = get(item.getId());
        temp.setAmount(item.getAmount());
        temp.setDescription(item.getDescription());
        temp.setImageFileName(item.getImageFileName());
        temp.setName(item.getName());
        temp.setPrice(item.getPrice());
        temp.setProductId(item.getProductId());
        return itemRepository.save(temp);
    }

    @Override
    public void delete(Long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public Item create(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item getByProductAndUser(Long productId, Long userId) {
        return itemRepository.getByProductIdAndUserId(productId, userId);
    }
}
