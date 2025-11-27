package com.supernova.supermarket.repository;

import com.supernova.supermarket.dto.TopProductDTO;
import com.supernova.supermarket.entity.SaleDetail;
import com.supernova.supermarket.entity.SaleDetailId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SaleDetailRepository extends JpaRepository<SaleDetail, SaleDetailId> {

    @Query("SELECT new com.supernova.supermarket.dto.TopProductDTO(d.product.productId, d.product.name, SUM(d.quantity)) " +
            "FROM SaleDetail d GROUP BY d.product.productId, d.product.name ORDER BY SUM(d.quantity) DESC")
    List<TopProductDTO> findTopProducts(Pageable pageable);

    long countByProduct_ProductId(Long productId);
}
