package com.kpi.internetshop.service;

import com.kpi.internetshop.entity.Product;

public interface ProductService extends GenericService<Product, Long> {
    public Product create(Product item);

    public void deleteAmount(Product product, int amount);
}
