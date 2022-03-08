package com.whahn.sandbox.domain.creatorsettlement;

import com.whahn.sandbox.domain.creatorsettlement.dto.request.CreatorSettlementRequestDto;
import com.whahn.sandbox.domain.creatorsettlement.dto.response.CreatorSettlementResponseDto;
import com.whahn.sandbox.domain.creatorsettlement.v1.request.CreatorSettlementRequest;
import com.whahn.sandbox.domain.creatorsettlement.v1.response.CreatorSettlementResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.Collections;
import java.util.List;

import static com.whahn.sandbox.domain.creatorsettlement.dto.request.CreatorSettlementRequestDto.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreatorSettlementFacadeTest {
    @Mock
    private CreatorSettlementService creatorSettlementService;

    @InjectMocks
    private CreatorSettlementFacade creatorSettlementFacade;

    @Test
    @DisplayName("[성공] 크리에이터 기준 월별 정산 조회")
    void searchMonthlySettlementByCreatorId() {
        CreatorSettlementRequest.SearchMonthlySettlementByCreator request = CreatorSettlementRequest.SearchMonthlySettlementByCreator.builder()
                .searchStartMonth(YearMonth.of(2022, 3))
                .searchEndMonth(YearMonth.of(2022, 3))
                .build();

        CreatorSettlementAmountMonthlyWithCreatorIdRequest condition = CreatorSettlementAmountMonthlyWithCreatorIdRequest.builder()
                .creatorId(1L)
                .searchStartMonth(LocalDate.of(2022, 3, 1))
                .searchEndMonth(LocalDate.of(2022, 3, 31))
                .build();

        CreatorSettlementResponseDto.CreatorSettlementAmountWithCreatorIdMonthly response = CreatorSettlementResponseDto.CreatorSettlementAmountWithCreatorIdMonthly.builder()
                .channelId(1L)
                .channelName("test channel")
                .creatorId(1L)
                .creatorName("test")
                .settlementYearMonth("2022-03")
                .settlementAmount(BigDecimal.valueOf(1000))
                .build();

        Mockito.when(creatorSettlementService.findMonthlyCreatorSettlementByCreatorId(condition))
                        .thenReturn(Collections.singletonList(response));

        List<CreatorSettlementResponse.CreatorMonthlySettlementAmountWithCreatorIdResponse> result = creatorSettlementFacade.searchMonthlySettlementByCreatorId(1L, request);

        CreatorSettlementResponse.CreatorMonthlySettlementAmountWithCreatorIdResponse result1 = result.get(0);
        Assertions.assertThat(result.size()).isEqualTo(1);
        Assertions.assertThat(result1.getCreatorId()).isEqualTo(1L);
        Assertions.assertThat(result1.getChannelId()).isEqualTo(1L);
        Assertions.assertThat(result1.getSettlementAmount()).isEqualTo(BigDecimal.valueOf(1000L));
        Assertions.assertThat(result1.getSettlementYearMonth()).isEqualTo("2022-03");
    }
}