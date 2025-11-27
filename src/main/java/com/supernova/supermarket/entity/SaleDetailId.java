package com.supernova.supermarket.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class SaleDetailId implements Serializable {

    private Long saleId;
    private Long productId;
}
