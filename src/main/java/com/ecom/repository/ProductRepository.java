package com.ecom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecom.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public List<Product> findByCategoryName(String category);

    public Optional<Product> findBySlug(String slug);

    @Query("SELECT p from Product p where lower(p.title) LIKE lower(concat('%', :query, '%')) OR lower(p.description) LIKE lower(concat('%', :query, '%'))")
    public List<Product> searchProduct(@Param("query") String query);

}
