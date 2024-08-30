package com.ecom.service;

import java.util.List;

import com.ecom.model.ProductCategory;

public interface ProductCategoryService {

    public ProductCategory createProductCategory(ProductCategory productCategory);

    public List<ProductCategory> getAllProductCategories();

}
