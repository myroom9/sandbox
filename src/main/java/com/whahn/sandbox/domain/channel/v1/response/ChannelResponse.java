package com.whahn.sandbox.domain.channel.v1.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;

public class ChannelResponse {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChannelSalesAndCreatorSettlementAmount {
        private Long channelId;
        private String channelName;
        private YearMonth searchMonth;

        private List<CreatorSettlementAmount> creators;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreatorSettlementAmount {
        private Long creatorId;
        private String creatorName;
        private BigDecimal settlementAmount;
    }
}
