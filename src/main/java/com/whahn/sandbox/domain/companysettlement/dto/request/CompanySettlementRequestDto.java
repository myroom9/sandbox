package com.whahn.sandbox.domain.companysettlement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class CompanySettlementRequestDto {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CompanySettlementMonthly {
        private LocalDate searchStartMonth;
        private LocalDate searchEndMonth;
    }
}
