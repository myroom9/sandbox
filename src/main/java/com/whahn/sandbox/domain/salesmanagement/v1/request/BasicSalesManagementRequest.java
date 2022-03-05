package com.whahn.sandbox.domain.salesmanagement.v1.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class BasicSalesManagementRequest {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveRequest {
        @Schema(description = "유튜브 채널 고유번호")
        @NotNull
        private Long channelId;

        @Schema(description = "채널 수익 금액")
        @NotNull
        @Min(value = 1)
        private BigDecimal sales;

        @Schema(description = "채널 수익 발생일")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate salesDate;
    }
}
