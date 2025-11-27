package com.supernova.supermarket.service;

import com.supernova.supermarket.dto.BranchSalesReportDTO;
import com.supernova.supermarket.dto.TopProductDTO;
import com.supernova.supermarket.entity.Branch;
import com.supernova.supermarket.entity.Sale;
import com.supernova.supermarket.exception.ResourceNotFoundException;
import com.supernova.supermarket.repository.BranchRepository;
import com.supernova.supermarket.repository.SaleDetailRepository;
import com.supernova.supermarket.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final SaleRepository saleRepo;
    private final SaleDetailRepository saleDetailRepo;
    private final BranchRepository branchRepo;

    public BranchSalesReportDTO branchSales(Long branchId, LocalDate start, LocalDate end) {

        Branch branch = branchRepo.findById(branchId)
                .orElseThrow(() -> new ResourceNotFoundException("Sucursal no encontrada"));

        LocalDateTime startDt = start.atStartOfDay();
        LocalDateTime endDt = end.atTime(23, 59, 59);

        List<Sale> sales = saleRepo.findByBranchAndDateRange(branchId, startDt, endDt);

        long count = sales.size();
        double totalAmount = sales.stream()
                .mapToDouble(Sale::getTotal)
                .sum();

        return new BranchSalesReportDTO(branchId, branch.getName(), count, totalAmount);
    }

    public List<TopProductDTO> topProducts(int limit) {
        return saleDetailRepo.findTopProducts(PageRequest.of(0, limit));
    }
}
