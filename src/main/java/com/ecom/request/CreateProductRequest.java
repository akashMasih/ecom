package com.ecom.request;

import java.util.List;
import com.ecom.model.ProductCategory;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class CreateProductRequest {

    @NotBlank(message = "Product title cannot be blank")
    private String title;

    @NotBlank(message = "Product description cannot be blank")
    private String description;

    @DecimalMin(value = "0.01", message = "Product price cannot be less than 0.01")
    private double price;

    private List<String> images;

    @NotNull(message = "Product category cannot be null")
    private ProductCategory category;
}
