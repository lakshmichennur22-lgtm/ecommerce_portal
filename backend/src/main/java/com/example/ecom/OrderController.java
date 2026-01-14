package com.example.ecom;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(
    origins = "*",
    allowedHeaders = "*",
    methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}
)
public class OrderController {

    private final OrderRepository repo;

    public OrderController(OrderRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<OrderItem> getAllOrders() {
        return repo.findAll();
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("OK");
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderItem order) {
        if (order.getProductName() == null || order.getCustomerName() == null) {
            return ResponseEntity.badRequest().body("Missing required fields");
        }
        try {
            OrderItem saved = repo.save(order);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to save order: " + e.getMessage());
        }
    }
}
