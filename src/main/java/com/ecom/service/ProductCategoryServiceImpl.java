package com.ecom.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.exception.AlreadyExistException;
import com.ecom.exception.NotFoundException;
import com.ecom.model.ProductCategory;
import com.ecom.repository.ProductCategoryRepository;
import com.ecom.request.UpdateProductCategoryRequest;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategory createProductCategory(ProductCategory productCategory) throws AlreadyExistException {

        ProductCategory exitedCategory = productCategoryRepository.findByName(productCategory.getName());

        if (exitedCategory != null) {
            throw new AlreadyExistException("Product category already exists, create with different name");
        }

        return productCategoryRepository.save(productCategory);
    }

    @Override
    public List<ProductCategory> getAllProductCategories() {
        return productCategoryRepository.findAll();
    }

    @Override
    public void deleteProductCategory(Long id) throws NotFoundException {
        ProductCategory exitedCategory = getProductCategoryById(id);
        productCategoryRepository.deleteById(id);

    }

    @Override
    public ProductCategory updateProductCategory(Long id, UpdateProductCategoryRequest productCategory)
            throws NotFoundException, AlreadyExistException {

        ProductCategory exitedCategory = getProductCategoryById(id);

        if (exitedCategory.getName().equals(productCategory.getName())) {
            throw new AlreadyExistException("Product category already exists, create with different name");
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

    @Override
    public ProductCategory getProductCategoryById(Long id) throws NotFoundException {
        Optional<ProductCategory> exitedCategory = productCategoryRepository.findById(id);

        if (exitedCategory.isEmpty()) {
            throw new NotFoundException("Product category not found with id " + id);
        }
        return exitedCategory.get();
    }

}
