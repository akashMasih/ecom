package com.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.model.Product;
import com.ecom.request.CreateProductRequest;
import com.ecom.service.ProductService;

@RestController
@RequestMapping("/api/admin/product")
public class AdminProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest req) {
        Product product = productService.createProduct(req);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long id) throws Exception {
        Product product1 = productService.updateProduct(id, product);
        return new ResponseEntity<>(product1, HttpStatus.CREATED);
    }



}
