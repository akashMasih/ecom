package com.ecom.service;

import java.util.List;

import com.ecom.model.ProductCategory;
import com.ecom.request.UpdateProductCategoryRequest;

public interface ProductCategoryService {

    public ProductCategory createProductCategory(ProductCategory productCategory) throws Exception;

    public List<ProductCategory> getAllProductCategories();

    public void deleteProductCategory(Long id);

    public ProductCategory updateProductCategory(Long id, UpdateProductCategoryRequest productCategory)
            throws Exception;

}
