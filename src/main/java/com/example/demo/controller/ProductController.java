package com.example.demo.controller;

import com.example.demo.entity.DTO.ProductDTO;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ProductController(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        logger.info("Retrieved all products");
        List<Product> products = productRepository.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("products/{id}")
    public ResponseEntity<Optional<Product>> getProduct(@PathVariable Long id) {
        logger.info("Retrieved product with id {} ", id);
        Optional<Product> product = productRepository.findById(id);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@RequestBody ProductDTO updateProduct, @PathVariable Long id) {
        logger.info("Update course with id: {}", id);
        Optional<Product> product = productRepository.findById(id);
        return product.map(p -> {
            p.setTitle(updateProduct.getTitle());
            p.setDescription((updateProduct.getDescription()));
            p.setPrice(updateProduct.getPrice());
            return productRepository.save(p);
        }).orElseGet(() -> {
            updateProduct.setId(id);
            return productRepository.save(updateProduct);
        });
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product newProduct) {
        logger.info("Add new product with id: {}", newProduct.getId());
        return productRepository.save(newProduct);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        logger.info("Delete product with id: {}", id);
        productRepository.deleteById(id);
    }

    @GetMapping("/productsByUserId")
    public List<Product> productsByUserId(@RequestParam("userId") Long userId) {
        return productService.findAllById(userId);
    }

}
