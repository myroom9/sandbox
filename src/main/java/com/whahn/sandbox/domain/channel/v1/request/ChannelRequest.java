package com.whahn.sandbox.domain.channel.v1.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.YearMonth;

public class ChannelRequest {
    @Data
    @Builder
    public static class SearchSalesAmount {
        @Schema(description = "조회 시작 월", type = "string", required = true, format = "yearmonth", example = "2022-02")
        @DateTimeFormat(pattern = "yyyy-MM")
        private YearMonth searchStartMonth;
        @Schema(description = "조회 종료 월", type = "string", required = true, format = "yearmonth", example = "2022-03")
        @DateTimeFormat(pattern = "yyyy-MM")
        private YearMonth searchEndMonth;
    }
}
