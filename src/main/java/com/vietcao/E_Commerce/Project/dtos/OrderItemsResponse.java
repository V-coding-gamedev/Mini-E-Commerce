package com.vietcao.E_Commerce.Project.dtos;

public record OrderItemsResponse(
    int cart_item_id,
    int cart_id,
    long product_id,
    long quantity,
    float price
) {}

