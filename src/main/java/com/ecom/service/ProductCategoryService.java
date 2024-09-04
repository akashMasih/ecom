package com.ecom.service;

import java.util.List;

import com.ecom.exception.AlreadyExistException;
import com.ecom.exception.NotFoundException;
import com.ecom.model.ProductCategory;
import com.ecom.request.UpdateProductCategoryRequest;

public interface ProductCategoryService {

    public ProductCategory createProductCategory(ProductCategory productCategory) throws AlreadyExistException;

    public List<ProductCategory> getAllProductCategories();

    public void deleteProductCategory(Long id) throws NotFoundException;

    public ProductCategory updateProductCategory(Long id, UpdateProductCategoryRequest productCategory)
            throws AlreadyExistException, NotFoundException;

    public ProductCategory getProductCategoryById(Long id) throws NotFoundException;

}
