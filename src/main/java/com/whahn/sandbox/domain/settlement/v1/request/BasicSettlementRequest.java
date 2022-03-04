package com.whahn.sandbox.domain.settlement.v1.request;

import com.whahn.sandbox.domain.channel.Channel;
import com.whahn.sandbox.domain.settlement.Settlement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BasicSettlementRequest {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveRequest {
        private Long channelId;
        private BigDecimal creatorSettlementAmount;
        private BigDecimal companySettlementAmount;
        private LocalDate settlementDate;

        public Settlement toEntity(Channel channel) {
            return new Settlement(this, channel);
        }
    }
}
