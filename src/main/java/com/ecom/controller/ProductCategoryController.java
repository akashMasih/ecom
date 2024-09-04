package com.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.model.ProductCategory;
import com.ecom.service.ProductCategoryService;

@RestController
@RequestMapping("/public/api/product-category")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping
    ResponseEntity<List<ProductCategory>> getAll() {

        List<ProductCategory> productCategories = productCategoryService.getAllProductCategories();
        return new ResponseEntity<>(productCategories, HttpStatus.OK);

    }

}
