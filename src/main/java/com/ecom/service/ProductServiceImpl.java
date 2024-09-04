package com.ecom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.exception.NotFoundException;
import com.ecom.model.Product;
import com.ecom.repository.ProductRepository;
import com.ecom.request.CreateProductRequest;
import com.ecom.utils.SlugUtil;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(CreateProductRequest req) {
        Product product = new Product();
        product.setTitle(req.getTitle());
        product.setDescription(req.getDescription());
        product.setPrice(req.getPrice());
        product.setImages(req.getImages());
        product.setCategory(req.getCategory());
        String baseSlug = SlugUtil.toSlug(req.getTitle());
        String uniqueSlug = generateUniqueSlug(baseSlug);
        product.setSlug(uniqueSlug);

        return productRepository.save(product);

    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) throws NotFoundException {

        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new NotFoundException("Product not found with id " + id);
        } else {
            return product.get();
        }

    }

    @Override
    public List<Product> getProductsByCategory(String category) {

        return productRepository.findByCategoryName(category);
    }

    @Override
    public void deleteProductById(Long id) throws Exception {

        getProductById(id);
        productRepository.deleteById(id);
    }

    @Override
    public Product getProductBySlug(String slug) throws NotFoundException {

        return productRepository.findBySlug(slug)
                .orElseThrow(() -> new NotFoundException("Product not found with slug " + slug));
    }

    private String generateUniqueSlug(String baseSlug) {
        String slug = baseSlug;
        int count = 1;

        while (existedBySlug(slug)) { // Assuming existsBySlug method exists in the repository
            slug = baseSlug + "-" + count++;
        }
        return slug;
    }

    @Override
    public boolean existedBySlug(String slug) {

        Optional<Product> product = productRepository.findBySlug(slug);
        if (product.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public Product updateProduct(Long id, Product product) throws Exception {

        Product existingProduct = getProductById(id);

        if (product.getTitle() != null) {
            existingProduct.setTitle(product.getTitle());
        }

        if (product.getDescription() != null) {
            existingProduct.setDescription(product.getDescription());
        }

        if (product.getPrice() != 0) {
            existingProduct.setPrice(product.getPrice());
        }

        if (product.getImages() != null) {
            existingProduct.setImages(product.getImages());
        }

        if (product.getCategory() != null) {
            existingProduct.setCategory(product.getCategory());
        }

        return productRepository.save(existingProduct);
    }

    @Override
    public List<Product> searchProduct(String keyword) {

        return productRepository.searchProduct(keyword);
    }

}
