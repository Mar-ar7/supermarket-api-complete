package com.supernova.supermarket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TopProductDTO {

    private Long productId;
    private String productName;
    private Long totalQuantity; // 👈 antes Integer, ahora Long

}
