package com.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.model.ProductCategory;
import com.ecom.service.ProductCategoryService;

@RestController
@RequestMapping("/api/admin/product-category")
public class AdminProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @PostMapping
    ResponseEntity<ProductCategory> createProductCategory(@RequestBody ProductCategory productCategory) {

        return ResponseEntity.ok(productCategoryService.createProductCategory(productCategory));

    }

}
