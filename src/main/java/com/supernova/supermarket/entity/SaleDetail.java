package com.supernova.supermarket.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sale_details")
@Data
public class SaleDetail {

    @EmbeddedId
    private SaleDetailId id = new SaleDetailId();


    @ManyToOne
    @MapsId("saleId")
    @JoinColumn(name = "sale_id")
    @JsonIgnore
    private Sale sale;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;
    private Double unitPrice;
    private Double subtotal;
}
