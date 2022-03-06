package com.whahn.sandbox.domain.companysettlement.v1.response;

import com.whahn.sandbox.common.ModelMapperUtil;
import io.swagger.v3.oas.annotations.media.Schema;
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
        @Schema(description = "회사 총 매출", required = true)
        private BigDecimal totalSales;
        @Schema(description = "회사 총 정산금액", required = true)
        private BigDecimal settlementAmount;
        @Schema(description = "조회 월", type = "string", required = true, format = "yearmonth", example = "2022-02")
        private YearMonth targetYearMonth;
    }
}
