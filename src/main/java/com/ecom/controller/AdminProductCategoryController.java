package com.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.exception.AlreadyExistException;
import com.ecom.exception.NotFoundException;
import com.ecom.model.ProductCategory;
import com.ecom.request.UpdateProductCategoryRequest;
import com.ecom.service.ProductCategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin/product-category")
public class AdminProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @PostMapping
    ResponseEntity<ProductCategory> createProductCategory(@RequestBody @Valid ProductCategory productCategory)
            throws AlreadyExistException {

        return ResponseEntity.ok(productCategoryService.createProductCategory(productCategory));

    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteProductCategory(@PathVariable Long id) throws NotFoundException {
        productCategoryService.deleteProductCategory(id);
        return ResponseEntity.ok("product category has been deleted successfully");

    }

    @PutMapping("/{id}")
    ResponseEntity<ProductCategory> updateProductCategory(@RequestBody UpdateProductCategoryRequest productCategory,
            @PathVariable Long id)
            throws NotFoundException, AlreadyExistException {

        return ResponseEntity.ok(productCategoryService.updateProductCategory(id, productCategory));

    }

}
