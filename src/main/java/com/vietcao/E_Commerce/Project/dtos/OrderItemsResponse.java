package com.vietcao.E_Commerce.Project.dtos;

public record OrderItemsResponse(
    long cartId,
    long productId,
    String productName,
    long quantity,
    float price
) {}


