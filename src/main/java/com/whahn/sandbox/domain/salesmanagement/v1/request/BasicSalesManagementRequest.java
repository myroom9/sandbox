package com.whahn.sandbox.domain.salesmanagement.v1.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BasicSalesManagementRequest {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveRequest {
        private Long channelId;
        private BigDecimal sales;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate salesDate;
    }
}
