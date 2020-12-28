package com.kpi.internetshop.controller;

import com.kpi.internetshop.entity.Product;
import com.kpi.internetshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainController {
    private ProductService productService;

    public MainController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Product> products = productService.getAll().stream().filter(product -> product.getAmount() > 0).collect(Collectors.toList());
        model.addAttribute("products", products);
        return "index";
    }
    @GetMapping("/accessDenied")
    public String accessDeniedPage() {
        return "access_denied";
    }
}
