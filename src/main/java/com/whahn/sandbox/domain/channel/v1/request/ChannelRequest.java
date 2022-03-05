package com.whahn.sandbox.domain.channel.v1.request;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.YearMonth;

public class ChannelRequest {
    @Data
    @Builder
    public static class SearchSalesAmount {
        @DateTimeFormat(pattern = "yyyy-MM")
        private YearMonth searchStartMonth;
        @DateTimeFormat(pattern = "yyyy-MM")
        private YearMonth searchEndMonth;
    }
}
