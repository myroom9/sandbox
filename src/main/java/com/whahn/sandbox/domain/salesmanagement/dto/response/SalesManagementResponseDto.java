package com.whahn.sandbox.domain.salesmanagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.YearMonth;

public class SalesManagementResponseDto {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChannelSalesMonthly {
        private Long channelId;
        private String channelName;
        private BigDecimal salesAmount;
        private String salesYearMonth;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TotalSalesMonthly {
        private BigDecimal salesAmount;
        private String salesYearMonth;
    }
}
