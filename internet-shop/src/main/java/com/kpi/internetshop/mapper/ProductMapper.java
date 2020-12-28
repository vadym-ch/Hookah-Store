package com.kpi.internetshop.mapper;

import com.kpi.internetshop.entity.Product;
import com.kpi.internetshop.entity.dto.response.ProductResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductResponseDto mapFromProductToProductResponseDto(Product product) {
        return new ProductResponseDto(product.getName(), product.getDescription(), product.getAmount(), product.getPrice());
    }
}
