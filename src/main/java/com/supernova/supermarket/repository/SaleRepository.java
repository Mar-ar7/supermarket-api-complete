package com.supernova.supermarket.repository;

import com.supernova.supermarket.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    List<Sale> findByBranch_BranchId(Long branchId);

    @Query("SELECT s FROM Sale s WHERE s.branch.branchId = :branchId AND s.datetime BETWEEN :start AND :end")
    List<Sale> findByBranchAndDateRange(@Param("branchId") Long branchId,
                                        @Param("start") LocalDateTime start,
                                        @Param("end") LocalDateTime end);
}
