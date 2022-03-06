package com.whahn.sandbox.domain.companysettlement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

public class CompanySettlementResponseDto {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CompanySettlementMonthlyReturn {
        private BigDecimal companySettlementAmount;
        private String settlementYearMonth;
    }
}
