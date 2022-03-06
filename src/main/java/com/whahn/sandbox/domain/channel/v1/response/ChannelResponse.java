package com.whahn.sandbox.domain.channel.v1.response;

import io.swagger.v3.oas.annotations.media.Schema;
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
        @Schema(description = "유튜브 채널 고유번호", required = true)
        private Long channelId;
        @Schema(description = "유튜브 채널명", type = "string", required = true)
        private String channelName;
        @Schema(description = "조회 월", type = "string", required = true, format = "yearmonth", example = "2022-02")
        private YearMonth searchMonth;
        @Schema(description = "유튜브 채널 수익 금액", required = true)
        private BigDecimal salesAmount;
        @Schema(description = "유튜브 크리에이터 정산 금액 리스트", required = true)
        private List<CreatorSettlementAmount> creators;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreatorSettlementAmount {
        @Schema(description = "유튜브 크리에이터 고유번호", required = true)
        private Long creatorId;
        @Schema(description = "유튜브 크리에이터명", required = true)
        private String creatorName;
        @Schema(description = "유튜브 크리에이터 정산 금액", required = true)
        private BigDecimal settlementAmount;
    }
}
