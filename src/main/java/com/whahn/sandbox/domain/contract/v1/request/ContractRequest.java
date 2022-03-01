package com.whahn.sandbox.domain.contract.v1.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class ContractRequest {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Save {
        private Channel channel;
        private List<Creator> creators;
        private Contract contract;
    }

    @Data
    @Builder
    public static class Channel {
        @Schema(description = "유튜브 채널명")
        private String name;
    }

    @Data
    @Builder
    public static class Creator {
        @Schema(description = "유튜브 크리에이터명")
        private String name;
        @Schema(description = "유튜브 크리에이터 생년월일")
        private String birth;
        @Schema(description = "유튜브 크리에이터 성별")
        private int sex;
    }

    @Data
    @Builder
    public static class Contract {
        @Schema(description = "유튜브 크리에이터 요율")
        private int creatorRate;
        @Schema(description = "회사 요율")
        private int companyRate;
    }
}
