package com.ecom.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.model.ProductCategory;
import com.ecom.repository.ProductCategoryRepository;
import com.ecom.request.UpdateProductCategoryRequest;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategory createProductCategory(ProductCategory productCategory) throws Exception {

        ProductCategory exitedCategory = productCategoryRepository.findByName(productCategory.getName());

        if (exitedCategory != null) {
            throw new Exception("Product category already exists");
        }

        return productCategoryRepository.save(productCategory);
    }

    @Override
    public List<ProductCategory> getAllProductCategories() {
        return productCategoryRepository.findAll();
    }

    @Override
    public void deleteProductCategory(Long id) {
        productCategoryRepository.deleteById(id);

    }

    @Override
    public ProductCategory updateProductCategory(Long id, UpdateProductCategoryRequest productCategory)
            throws Exception {
        ProductCategory exitedCategory = productCategoryRepository.findByName(productCategory.getName());
        if (exitedCategory != null) {
            throw new Exception("Product category already exists");
        }
        if (id == null) {
            throw new Exception("Product category id cannot be null");
        }

        ProductCategory newProductCategory = new ProductCategory();
        newProductCategory.setName(productCategory.getName());
        newProductCategory.setId(id);

        if (productCategory.getDescription() != null) {
            newProductCategory.setDescription(productCategory.getDescription());
        }
        newProductCategory.setLastModifiedDate(LocalDateTime.now());

        return productCategoryRepository.save(newProductCategory);

    }

}
