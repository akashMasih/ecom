package com.ecom.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory extends BaseEntity {

    @NotBlank(message = "Category Name cannot be blank")
    private String name;

    private String description;

}
