package com.supernova.supermarket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleResponseDTO {

    private Long saleId;
    private LocalDateTime datetime;
    private Double total;
    private Long branchId;
    private List<SaleItemDTO> items;


    public SaleResponseDTO(Long saleId,
                           LocalDateTime datetime,
                           Long branchId,
                           Double total,
                           List<SaleItemDTO> items) {
        this.saleId = saleId;
        this.datetime = datetime;
        this.branchId = branchId;
        this.total = total;
        this.items = items;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SaleItemDTO {
        private Long productId;
        private String productName;
        private Integer quantity;
        private Double price;
        private Double subtotal;
    }
}
