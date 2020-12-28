package com.kpi.internetshop.controller;

import com.kpi.internetshop.entity.Item;
import com.kpi.internetshop.entity.Product;
import com.kpi.internetshop.entity.ShoppingCart;
import com.kpi.internetshop.service.ItemService;
import com.kpi.internetshop.service.ProductService;
import com.kpi.internetshop.service.ShoppingCartService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ShoppingCartController {
    private ShoppingCartService shoppingCartService;
    private ProductService productService;
    private ItemService itemService;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  ProductService productService,
                                  ItemService itemService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
        this.itemService = itemService;
    }

    @GetMapping("/cart/show")
    public String showCart(Authentication authentication, Model model) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        ShoppingCart cart = shoppingCartService.getByUserEmail(principal.getUsername());
      List<Item> items = cart.getItems();
      if(items.size() != 0) {
      model.addAttribute("products", items);
      model.addAttribute("sum", shoppingCartService.getSumOfProducts(cart));
            return "cart";
      }
      return "empty_cart";
    }

    @GetMapping("/cart/clean")
    public String cleanCart(Authentication authentication) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        shoppingCartService.clear(shoppingCartService.getByUserEmail(principal.getUsername()));
        return "empty_cart";
    }

    @GetMapping("/cart/delete/{id}")
    public String deletePosition(@PathVariable Long id, Authentication authentication) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        ShoppingCart cart = shoppingCartService.getByUserEmail(principal.getUsername());
        shoppingCartService.deleteItem(itemService.get(id), cart);
        return "redirect:/cart/show";
    }

    @GetMapping("/cart/add/{id}")
    public String addPosition(@PathVariable Long id, Authentication authentication) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        ShoppingCart cart = shoppingCartService.getByUserEmail(principal.getUsername());
        List<Item> items = cart.getItems();
        Product product = productService.get(id);
        Long userId = cart.getUser().getId();
        if (items.isEmpty() == true || items.stream()
                .filter(item -> item.getProductId() == id && item.getUserId() == userId)
                .count() == 0) { // if in itemList there are no items with this product id and user id ->
            Item item = new Item(); // create new item
            item.setProductId(product.getId());
            item.setName(product.getName());
            item.setImageFileName(product.getImageFileName());
            item.setDescription(product.getDescription());
            item.setPrice(product.getPrice());
            item.setAmount(1);
            item.setUserId(userId); // set fields
            itemService.create(item); // save to db
            shoppingCartService.addItem(item, cart);
        } else {
            Item item1 = cart.getItems().stream().filter(item -> item.getProductId() == id).findAny().get(); // take item that already in cart
           shoppingCartService.addItem(item1, cart);
            // update item in db...
       }
       productService.deleteAmount(productService.get(id), 1);
        return "redirect:/";
    }

    @GetMapping("/cart/update/{id}")
    public String showUpdateForm(@PathVariable ( value = "id") Long id, Model model) {
        Item item = itemService.get(id);
        model.addAttribute("item", item);
        return "update_cart";
    }

    @PostMapping("/cart/save")
    public String saveProduct(@ModelAttribute("item") Item item) {
        Product product = productService.get(item.getProductId());
        if(product.getAmount() < item.getAmount()) {
            return "redirect:/cart/update/" + item.getId()+ "?success";
        }
        int oldAmount = itemService.get(item.getId()).getAmount();
        itemService.update(item);
        productService.deleteAmount(product, itemService.get(item.getId()).getAmount() - oldAmount);
        return "redirect:/cart/show";
    }
}
