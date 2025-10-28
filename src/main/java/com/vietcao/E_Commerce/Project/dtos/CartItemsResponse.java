package com.vietcao.E_Commerce.Project.dtos;

public record CartItemsResponse(
    Long productId,
    String name,
    Float price,
    String imageUrl,
    Long quantity
) {}
