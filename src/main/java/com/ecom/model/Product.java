package com.ecom.model;

import java.util.List;
import com.ecom.model.dto.RatingDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {

    private String title;
    private String description;
    private double price;

    @Column(length = 1000)
    private List<String> images;
    private String slug;

    private RatingDto rating;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id")
    private ProductCategory category;

}
