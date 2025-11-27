package com.supernova.supermarket.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String name;
    private String category;
    private Double price;

    @Column(unique = true, nullable = false)
    private String barcode;


    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<SaleDetail> saleDetails;
}
