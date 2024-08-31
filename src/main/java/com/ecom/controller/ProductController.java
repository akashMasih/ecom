package com.ecom.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.ecom.model.Product;
import com.ecom.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    ResponseEntity<List<Product>> getAll() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("slug/{slug}")
    ResponseEntity<Product> getBySlug(@PathVariable String slug) throws Exception {
        Product product = productService.getProductBySlug(slug);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteProduct(@PathVariable Long id) throws Exception {
        productService.deleteProductById(id);
        return new ResponseEntity<>("product has been deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/search")
    ResponseEntity<List<Product>> searchProduct(@RequestParam String query)
            throws Exception {
        return new ResponseEntity<>(productService.searchProduct(query),
                HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) throws Exception {
        return new ResponseEntity<>(productService.getProductsByCategory(category), HttpStatus.OK);
    }

}
