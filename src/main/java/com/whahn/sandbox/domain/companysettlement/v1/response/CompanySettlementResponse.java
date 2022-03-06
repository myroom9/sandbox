package com.whahn.sandbox.domain.companysettlement.v1.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.YearMonth;

public class CompanySettlementResponse {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CompanyTotalSalesAndSettlement {
        private BigDecimal totalSales;
        private BigDecimal settlementAmount;
        private YearMonth targetYearMonth;
    }
}
