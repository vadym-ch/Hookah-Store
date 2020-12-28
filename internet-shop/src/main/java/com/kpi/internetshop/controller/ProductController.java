package com.kpi.internetshop.controller;

import com.kpi.internetshop.entity.Product;
import com.kpi.internetshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String getAll(Model model) {
        List<Product> productList = productService.getAll();
        model.addAttribute("productList", productList);
        return "allProducts";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable (value = "id") Long id) {
        productService.delete(id);
        return "redirect:/products";
    }

    @GetMapping("/products/update/{id}")
    public String showUpdateForm(@PathVariable ( value = "id") Long id, Model model) {
        Product product = productService.get(id);
        model.addAttribute("product", product);
        return "update_product";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.create(product);
        return "redirect:/products";
    }

    @GetMapping("/products/add")
    public String showNewProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "new_product";
    }
}
