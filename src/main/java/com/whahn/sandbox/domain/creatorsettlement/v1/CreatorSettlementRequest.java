package com.whahn.sandbox.domain.creatorsettlement.v1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.YearMonth;

public class CreatorSettlementRequest {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchMonthlySettlementByCreator {
        @DateTimeFormat(pattern = "yyyy-MM")
        private YearMonth searchStartMonth;
        @DateTimeFormat(pattern = "yyyy-MM")
        private YearMonth searchEndMonth;
    }
}
