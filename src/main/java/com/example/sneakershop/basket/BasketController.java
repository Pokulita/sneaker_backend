package com.example.sneakershop.basket;

import com.example.sneakershop.order.OrderService;
import com.example.sneakershop.product.Product;
import com.example.sneakershop.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/basket")
public class BasketController {

    private final BasketService basketService;
    private final OrderService orderService;

    @Autowired
    public BasketController(BasketService basketService, OrderService orderService) {
        this.basketService = basketService;
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<Basket> getBasketForUser(@RequestParam Long userId) {
        Basket basket = basketService.getBasketForUser(userId);
        return ResponseEntity.ok(basket);
    }

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestParam Long userId, @RequestBody Product product, @RequestParam int amount) {
        basketService.addProductToBasket(userId, product, amount);
        return ResponseEntity.ok("Product added successfully");
    }

    @PutMapping
    public ResponseEntity<String> updateProduct(@RequestParam Long userId, @RequestBody Product product, @RequestParam int amount) {
        basketService.updateProductInBasket(userId, product, amount);
        return ResponseEntity.ok("Product updated successfully");
    }

    @GetMapping("/total")
    public ResponseEntity<String> calculateTotal(@RequestParam Long userId) {
        BigDecimal total = basketService.calculateBasketTotal(userId);
        return ResponseEntity.ok("Total price is: " + total);
    }

    @DeleteMapping
    public ResponseEntity<String> removeProduct(@RequestParam Long userId, @RequestBody Product product) {
        basketService.removeProductFromBasket(userId, product);
        return ResponseEntity.ok("Product removed successfully");
    }

    @DeleteMapping("/clear")
    public ResponseEntity<String> clearBasket(@RequestParam Long userId) {
        basketService.clearBasket(userId);
        return ResponseEntity.ok("Basket cleared successfully");
    }

    @PostMapping("/checkout")
    public ResponseEntity<String> checkoutBasket(@RequestParam Long userId, @RequestParam Long orderId) {
        basketService.checkoutBasket(userId, orderId);
        return ResponseEntity.ok("Basket checked out successfully");
    }
}