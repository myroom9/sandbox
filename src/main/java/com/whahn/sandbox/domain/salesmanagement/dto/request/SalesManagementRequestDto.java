package com.whahn.sandbox.domain.salesmanagement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.YearMonth;

public class SalesManagementRequestDto {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChannelSalesMonthlyRequest {
        private Long channelId;
        private LocalDate searchStartMonth;
        private LocalDate searchEndMonth;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TotalSalesMonthlyRequest {
        private LocalDate searchStartMonth;
        private LocalDate searchEndMonth;
    }
}
