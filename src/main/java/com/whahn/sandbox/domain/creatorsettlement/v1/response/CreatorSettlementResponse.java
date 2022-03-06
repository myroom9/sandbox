package com.whahn.sandbox.domain.creatorsettlement.v1.response;

import com.whahn.sandbox.common.ModelMapperUtil;
import com.whahn.sandbox.domain.creatorsettlement.dto.response.CreatorSettlementResponseDto;
import com.whahn.sandbox.domain.creatorsettlement.dto.response.CreatorSettlementResponseDto.CreatorSettlementAmountWithCreatorIdMonthly;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

public class CreatorSettlementResponse {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreatorMonthlySettlementAmountWithCreatorIdResponse {
        private Long channelId;
        private String channelName;
        private Long creatorId;
        private String creatorName;
        private BigDecimal settlementAmount;
        private String settlementYearMonth;

        public CreatorMonthlySettlementAmountWithCreatorIdResponse toEntity(CreatorSettlementAmountWithCreatorIdMonthly data) {
            return ModelMapperUtil.map(data, CreatorMonthlySettlementAmountWithCreatorIdResponse.class);
        }

    }
}
