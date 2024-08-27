package com.ecom.model.dto;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class RatingDto {

    private float rate;
    private int count;

}
