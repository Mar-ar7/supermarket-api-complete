package com.supernova.supermarket.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "branches")
@Data
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long branchId;

    private String name;
    private String address;
    private String phone;

    @Column(unique = true, nullable = false)
    private String branchCode;


    @OneToMany(mappedBy = "branch")
    @JsonIgnore
    private List<Sale> sales;
}
