package com.ecom.service;

import java.util.List;

import com.ecom.exception.NotFoundException;
import com.ecom.model.Product;
import com.ecom.request.CreateProductRequest;

public interface ProductService {

    public List<Product> getAllProducts();

    public Product getProductById(Long id) throws NotFoundException;

    public List<Product> getProductsByCategory(String category);

    public Product getProductBySlug(String slug) throws NotFoundException;

    public boolean existedBySlug(String slug);

    public void deleteProductById(Long id) throws Exception;

    public Product updateProduct(Long id, Product product) throws Exception;

    public Product createProduct(CreateProductRequest req);

    public List<Product> searchProduct(String keyword);

}
