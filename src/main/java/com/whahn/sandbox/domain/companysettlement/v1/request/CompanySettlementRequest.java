package com.whahn.sandbox.domain.companysettlement.v1.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.YearMonth;

public class CompanySettlementRequest {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CompanyTotalSalesAndSettlementMonthlyRequest {
        @DateTimeFormat(pattern = "yyyy-MM")
        private YearMonth searchStartMonth;
        @DateTimeFormat(pattern = "yyyy-MM")
        private YearMonth searchEndMonth;
    }
}
