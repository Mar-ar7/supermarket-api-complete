package com.supernova.supermarket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleDetailResponseDTO {

    private Long saleDetailId;
    private Long productId;
    private String productName;
    private Integer quantity;
    private Double priceAtSale;

}
