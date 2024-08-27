package com.ecom.request;

import lombok.Data;

@Data
public class AddCartItemRequest {

    private Long productId;
    private int quantity;

}
