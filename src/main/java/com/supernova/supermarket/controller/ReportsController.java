package com.supernova.supermarket.controller;

import com.supernova.supermarket.dto.BranchSalesReportDTO;
import com.supernova.supermarket.dto.TopProductDTO;
import com.supernova.supermarket.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class ReportsController {

    private final ReportService reportService;

    @GetMapping("/branch-sales")
    public BranchSalesReportDTO branchSales(@RequestParam("branch_id") Long branchId,
                                            @RequestParam("start") String start,
                                            @RequestParam("end") String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return reportService.branchSales(branchId, startDate, endDate);
    }

    @GetMapping("/top-products")
    public List<TopProductDTO> topProducts(@RequestParam(name = "limit", defaultValue = "5") int limit) {
        return reportService.topProducts(limit);
    }
}
