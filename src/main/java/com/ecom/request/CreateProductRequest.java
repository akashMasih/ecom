package com.ecom.request;

import java.util.List;
import com.ecom.model.ProductCategory;

import lombok.Data;


@Data
public class CreateProductRequest {

    private String title;
    private String description;
    private double price;
    private List<String> images;
    private ProductCategory category;
}
