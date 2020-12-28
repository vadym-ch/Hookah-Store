package com.kpi.internetshop.service.impl;

import com.kpi.internetshop.entity.Product;
import com.kpi.internetshop.repository.ProductRepository;
import com.kpi.internetshop.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(Product item) {
        return productRepository.save(item);
    }

    @Override
    public void deleteAmount(Product product, int amount) {
        product.setAmount(product.getAmount() - amount);
        update(product);
    }

    @Override
    public Product get(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product update(Product item) {
        Product product = get(item.getId());
        product.setAmount(item.getAmount());
        product.setName(item.getName());
        product.setDescription(item.getDescription());
        product.setPrice(item.getPrice());
        product.setImageFileName(item.getImageFileName());
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
