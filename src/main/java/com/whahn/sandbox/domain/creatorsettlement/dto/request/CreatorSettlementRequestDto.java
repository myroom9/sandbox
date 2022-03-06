package com.whahn.sandbox.domain.creatorsettlement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class CreatorSettlementRequestDto {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreatorSettlementAmountMonthlyRequest {
        private Long channelId;
        private LocalDate searchStartMonth;
        private LocalDate searchEndMonth;
    }
}
