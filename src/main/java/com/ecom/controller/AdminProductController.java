package com.ecom.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.model.Product;
import com.ecom.request.CreateProductRequest;
import com.ecom.response.MessageResponse;
import com.ecom.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin/product")
public class AdminProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    ResponseEntity<Product> createProduct(@RequestBody @Valid CreateProductRequest req) {
        Product product = productService.createProduct(req);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long id) throws Exception {
        Product product1 = productService.updateProduct(id, product);
        return new ResponseEntity<>(product1, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    ResponseEntity<MessageResponse> deleteProduct(@PathVariable Long id) throws Exception {
        productService.deleteProductById(id);
        MessageResponse response = new MessageResponse("product has been deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
