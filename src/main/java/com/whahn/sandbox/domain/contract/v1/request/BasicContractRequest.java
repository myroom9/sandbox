package com.whahn.sandbox.domain.contract.v1.request;

import com.whahn.sandbox.common.ModelMapperUtil;
import com.whahn.sandbox.domain.channel.Channel;
import com.whahn.sandbox.domain.creator.Creator;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class BasicContractRequest {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Save {
        @Valid
        private ChannelRequest channelRequest;
        @Valid
        private List<CreatorRequest> creatorsRequest;
        @Valid
        private ContractRequest contractRequest;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChannelRequest {
        @Schema(description = "유튜브 채널명")
        @NotBlank(message = "유튜브 채널명을 입력해주세요.")
        private String name;

        public Channel toEntity(ChannelRequest channelRequest) {
            return new Channel(channelRequest.name);
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreatorRequest {
        @Schema(description = "유튜브 크리에이터명")
        @NotBlank(message = "유튜브 크리에이터명을 입력해주세요.")
        private String name;
        @Schema(description = "유튜브 크리에이터 생년월일")
        @NotBlank(message = "크리에이터의 생년월일을 입력해주세요.")
        private String birth;
        @Schema(description = "유튜브 크리에이터 성별")
        @Min(value = 0, message = "성별은 0보다 작을 수 없습니다.")
        @Max(value = 2, message = "성별은 3보다 클 수 없습니다.")
        private int sex;

        public Creator toEntity(CreatorRequest creatorRequest, Channel channel) {
            Creator creator = new Creator(creatorRequest.name, creatorRequest.birth, creatorRequest.sex);
            return creator;
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ContractRequest { 
        @Schema(description = "유튜브 크리에이터 요율")
        @Min(value = 0, message = "크리에이터 요율이 0보다 작을 수 없습니다.")
        @Max(value = 100, message = "크리에이터 요율이 100을 넘을 수 없습니다.")
        private int creatorRate;
        @Schema(description = "회사 요율")
        @Min(value = 0, message = "회사 요율이 0보다 작을 수 없습니다.")
        @Max(value = 100, message = "회사 요율이 100을 넘을 수 없습니다.")
        private int companyRate;
    }
}
