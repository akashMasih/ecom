package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.model.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

}
