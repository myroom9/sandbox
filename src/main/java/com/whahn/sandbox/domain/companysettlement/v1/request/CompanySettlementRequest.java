package com.whahn.sandbox.domain.companysettlement.v1.request;

import io.swagger.v3.oas.annotations.media.Schema;
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
        @Schema(description = "조회 시작 월", type = "string", required = true, format = "yearmonth", example = "2022-02")
        @DateTimeFormat(pattern = "yyyy-MM")
        private YearMonth searchStartMonth;
        @Schema(description = "조회 종료 월", type = "string", required = true, format = "yearmonth", example = "2022-02")
        @DateTimeFormat(pattern = "yyyy-MM")
        private YearMonth searchEndMonth;
    }
}
