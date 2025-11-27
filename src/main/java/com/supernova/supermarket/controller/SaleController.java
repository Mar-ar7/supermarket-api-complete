package com.supernova.supermarket.controller;

import com.supernova.supermarket.dto.SaleRequestDTO;
import com.supernova.supermarket.dto.SaleResponseDTO;
import com.supernova.supermarket.entity.Sale;
import com.supernova.supermarket.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService service;

    @PostMapping("/")
    public ResponseEntity<SaleResponseDTO> create(@RequestBody SaleRequestDTO dto) {
        return ResponseEntity.ok(service.createSale(dto));
    }

    @GetMapping("/")
    public List<Sale> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Sale get(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/branch/{branchId}")
    public List<Sale> byBranch(@PathVariable Long branchId) {
        return service.findByBranch(branchId);
    }
}
