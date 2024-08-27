package com.ecom.service;

import java.util.List;

import com.ecom.model.Product;
import com.ecom.request.CreateProductRequest;

public interface ProductService {

    public List<Product> getAllProducts();

    public Product getProductById(Long id);

    public List<Product> getProductsByCategory(String category);

    public void deleteProductById(Long id);

    public Product updateProduct(Long id, Product product);

    public Product createProduct(CreateProductRequest req);

}
