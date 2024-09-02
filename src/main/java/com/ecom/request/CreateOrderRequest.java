package com.ecom.request;

import com.ecom.model.Address;

import lombok.Data;

@Data
public class CreateOrderRequest {

    private Address deliveryAddress;
}
