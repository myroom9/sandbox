package com.whahn.sandbox.domain.creatorsettlement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

public class CreatorSettlementResponseDto {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreatorSettlementAmountMonthly {
        private Long channelId;
        private Long creatorId;
        private String creatorName;
        private BigDecimal settlementAmount;
        private String settlementYearMonth;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreatorSettlementAmountWithCreatorIdMonthly {
        private Long channelId;
        private String channelName;
        private Long creatorId;
        private String creatorName;
        private BigDecimal settlementAmount;
        private String settlementYearMonth;
    }
}
