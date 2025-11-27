package com.supernova.supermarket.service;

import com.supernova.supermarket.dto.SaleRequestDTO;
import com.supernova.supermarket.dto.SaleResponseDTO;
import com.supernova.supermarket.entity.*;
import com.supernova.supermarket.exception.ResourceNotFoundException;
import com.supernova.supermarket.repository.BranchRepository;
import com.supernova.supermarket.repository.ProductRepository;
import com.supernova.supermarket.repository.SaleDetailRepository;
import com.supernova.supermarket.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository saleRepo;
    private final BranchRepository branchRepo;
    private final ProductRepository productRepo;
    private final SaleDetailRepository saleDetailRepo;

    public SaleResponseDTO createSale(SaleRequestDTO dto) {

        Branch branch = branchRepo.findById(dto.getBranchId())
                .orElseThrow(() -> new ResourceNotFoundException("Sucursal no existe"));

        Sale sale = new Sale();
        sale.setDatetime(LocalDateTime.now());
        sale.setBranch(branch);

        List<SaleDetail> details = new ArrayList<>();
        double total = 0.0;

        for (var item : dto.getItems()) {
            Product product = productRepo.findById(item.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto no existe: " + item.getProductId()));

            double unitPrice = product.getPrice();
            double subtotal = unitPrice * item.getQuantity();
            total += subtotal;

            SaleDetail detail = new SaleDetail();
            SaleDetailId id = new SaleDetailId();
            detail.setId(id);
            detail.setSale(sale);
            detail.setProduct(product);
            detail.setQuantity(item.getQuantity());
            detail.setUnitPrice(unitPrice);
            detail.setSubtotal(subtotal);
            details.add(detail);
        }

        sale.setTotal(total);
        sale.setDetails(details);

        Sale saved = saleRepo.save(sale);
        saleDetailRepo.saveAll(details);

        List<SaleResponseDTO.SaleItemDTO> items = new ArrayList<>();
        for (SaleDetail d : details) {
            items.add(new SaleResponseDTO.SaleItemDTO(
                    d.getProduct().getProductId(),
                    d.getProduct().getName(),
                    d.getQuantity(),
                    d.getUnitPrice(),
                    d.getSubtotal()
            ));
        }

        return new SaleResponseDTO(
                saved.getSaleId(),
                saved.getDatetime(),
                saved.getBranch().getBranchId(),
                saved.getTotal(),
                items
        );
    }

    public List<Sale> findAll() {
        return saleRepo.findAll();
    }

    public Sale findById(Long id) {
        return saleRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada"));
    }

    public List<Sale> findByBranch(Long branchId) {
        return saleRepo.findByBranch_BranchId(branchId);
    }
}
