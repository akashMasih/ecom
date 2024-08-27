package com.ecom.model;

import jakarta.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory extends BaseEntity {

    private String name;

    private String description;

}
