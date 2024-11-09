package com.example.actuator.controller;

import com.example.actuator.model.Product;
import com.example.actuator.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public Page<Product> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size) {
        return productService.getAllProducts(page, size);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @PostMapping()
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }

    @DeleteMapping("/all")
    public String deleteAllProducts() {
        return productService.deleteAllProduct();
    }
}
