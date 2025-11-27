package com.supernova.supermarket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BranchSalesReportDTO {

    private Long branchId;
    private String branchName;
    private Long totalSalesCount;
    private Double totalAmount;

}
