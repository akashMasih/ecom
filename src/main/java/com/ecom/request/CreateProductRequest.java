package com.ecom.request;

import java.util.List;

import lombok.Data;

@Data
public class CreateProductRequest {

    private String title;
    private String description;
    private double price;
    private List<String> images;

}
