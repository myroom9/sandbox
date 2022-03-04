package com.whahn.sandbox.domain.salesmanagement.v1.request;

import com.whahn.sandbox.domain.channel.Channel;
import com.whahn.sandbox.domain.settlement.Settlement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
        private LocalDate salesDate;

        /*public Settlement toEntity(Channel channel) {
            return new Settlement(this, channel);
        }*/
    }
}
