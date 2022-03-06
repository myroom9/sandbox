package com.whahn.sandbox.domain.creatorsettlement.v1.response;

import com.whahn.sandbox.common.ModelMapperUtil;
import com.whahn.sandbox.domain.creatorsettlement.dto.response.CreatorSettlementResponseDto;
import com.whahn.sandbox.domain.creatorsettlement.dto.response.CreatorSettlementResponseDto.CreatorSettlementAmountWithCreatorIdMonthly;
import io.swagger.v3.oas.annotations.media.Schema;
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
        @Schema(description = "유튜브 채널 고유번호", required = true)
        private Long channelId;
        @Schema(description = "유튜브 채널명", required = true)
        private String channelName;
        @Schema(description = "유튜브 크리에이터 고유번호", required = true)
        private Long creatorId;
        @Schema(description = "유튜브 크리에이터명", required = true)
        private String creatorName;
        @Schema(description = "유튜브 크리에이터 월 정산 금액", required = true)
        private BigDecimal settlementAmount;
        @Schema(description = "조회 월", required = true)
        private String settlementYearMonth;

        public CreatorMonthlySettlementAmountWithCreatorIdResponse toEntity(CreatorSettlementAmountWithCreatorIdMonthly data) {
            return ModelMapperUtil.map(data, CreatorMonthlySettlementAmountWithCreatorIdResponse.class);
        }

    }
}
