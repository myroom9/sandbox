package com.whahn.sandbox.domain.channel;

import com.whahn.sandbox.domain.channel.v1.request.ChannelRequest;
import com.whahn.sandbox.domain.channel.v1.response.ChannelResponse;
import com.whahn.sandbox.domain.creatorsettlement.CreatorSettlementService;
import com.whahn.sandbox.domain.creatorsettlement.dto.request.CreatorSettlementRequestDto;
import com.whahn.sandbox.domain.creatorsettlement.dto.response.CreatorSettlementResponseDto;
import com.whahn.sandbox.domain.salesmanagement.SalesManagementService;
import com.whahn.sandbox.domain.salesmanagement.dto.request.SalesManagementRequestDto;
import com.whahn.sandbox.domain.salesmanagement.dto.response.SalesManagementResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ChannelFacadeTest {
    @Mock
    private SalesManagementService salesManagementService;

    @Mock
    private CreatorSettlementService creatorSettlementService;

    @InjectMocks
    private ChannelFacade channelFacade;

    @Test
    @DisplayName("[성공] 파사드 월별 채널 수익과 크리에이터 정산금 조회 테스트")
    void searchSalesAndCreatorSettlementSuccessTest() {
        Long channelId = 1L;
        ChannelRequest.SearchSalesAmount request = ChannelRequest.SearchSalesAmount.builder()
                .searchStartMonth(YearMonth.parse("2022-02"))
                .searchEndMonth(YearMonth.parse("2022-03"))
                .build();

        SalesManagementRequestDto.ChannelSalesMonthlyRequest condition = new SalesManagementRequestDto.ChannelSalesMonthlyRequest(channelId, request.getSearchStartMonth().atDay(1), request.getSearchEndMonth().atEndOfMonth());

        SalesManagementResponseDto.ChannelSalesMonthly channelSalesMonthly = SalesManagementResponseDto.ChannelSalesMonthly.builder()
                .channelId(1L)
                .channelName("test channel")
                .salesAmount(BigDecimal.valueOf(1000))
                .salesYearMonth("2022-03")
                .build();

        CreatorSettlementRequestDto.CreatorSettlementAmountMonthlyRequest creatorSettlementCondition = new CreatorSettlementRequestDto.CreatorSettlementAmountMonthlyRequest(channelId, request.getSearchStartMonth().atDay(1), request.getSearchEndMonth().atEndOfMonth());
        CreatorSettlementResponseDto.CreatorSettlementAmountMonthly creatorSettlementAmountMonthly = CreatorSettlementResponseDto.CreatorSettlementAmountMonthly.builder()
                .channelId(1L)
                .creatorId(1L)
                .creatorName("test creator")
                .settlementAmount(BigDecimal.valueOf(700))
                .settlementYearMonth("2022-03")
                .build();

        Mockito.when(salesManagementService.findMonthlyChannelSalesByChannelIdAndYearMonth(condition))
                .thenReturn(Collections.singletonList(channelSalesMonthly));

        Mockito.when(creatorSettlementService.findMonthlyCreatorSettlementByChannelId(creatorSettlementCondition))
                .thenReturn(Collections.singletonList(creatorSettlementAmountMonthly));

        List<ChannelResponse.ChannelSalesAndCreatorSettlementAmount> result = channelFacade.searchSalesAndCreatorSettlement(channelId, request);

        ChannelResponse.ChannelSalesAndCreatorSettlementAmount channelResult = result.get(0);
        ChannelResponse.CreatorSettlementAmount creatorResult = channelResult.getCreators().get(0);

        Assertions.assertThat(result.size()).isEqualTo(1);
        Assertions.assertThat(channelResult.getChannelId()).isEqualTo(1);
        Assertions.assertThat(channelResult.getChannelName()).isEqualTo("test channel");
        Assertions.assertThat(channelResult.getSearchMonth()).isEqualTo(YearMonth.parse("2022-03"));
        Assertions.assertThat(channelResult.getSalesAmount()).isEqualTo(BigDecimal.valueOf(1000));
        Assertions.assertThat(channelResult.getCreators().size()).isEqualTo(1);
        Assertions.assertThat(creatorResult.getCreatorId()).isEqualTo(1);
        Assertions.assertThat(creatorResult.getCreatorName()).isEqualTo("test creator");
        Assertions.assertThat(creatorResult.getSettlementAmount()).isEqualTo(BigDecimal.valueOf(700));
    }


    @Test
    @DisplayName("[성공] 파사드 월별 채널 수익과 다수 크리에이터 정산금 조회 테스트")
    void searchSalesAndCreatorSettlementSucessTest2() {
        Long channelId = 1L;
        ChannelRequest.SearchSalesAmount request = ChannelRequest.SearchSalesAmount.builder()
                .searchStartMonth(YearMonth.parse("2022-02"))
                .searchEndMonth(YearMonth.parse("2022-03"))
                .build();

        SalesManagementRequestDto.ChannelSalesMonthlyRequest condition = new SalesManagementRequestDto.ChannelSalesMonthlyRequest(channelId, request.getSearchStartMonth().atDay(1), request.getSearchEndMonth().atEndOfMonth());

        SalesManagementResponseDto.ChannelSalesMonthly channelSalesMonthly = SalesManagementResponseDto.ChannelSalesMonthly.builder()
                .channelId(1L)
                .channelName("test channel")
                .salesAmount(BigDecimal.valueOf(1000))
                .salesYearMonth("2022-03")
                .build();

        CreatorSettlementRequestDto.CreatorSettlementAmountMonthlyRequest creatorSettlementCondition = new CreatorSettlementRequestDto.CreatorSettlementAmountMonthlyRequest(channelId, request.getSearchStartMonth().atDay(1), request.getSearchEndMonth().atEndOfMonth());
        CreatorSettlementResponseDto.CreatorSettlementAmountMonthly creatorSettlementAmountMonthly = CreatorSettlementResponseDto.CreatorSettlementAmountMonthly.builder()
                .channelId(1L)
                .creatorId(1L)
                .creatorName("test creator")
                .settlementAmount(BigDecimal.valueOf(350))
                .settlementYearMonth("2022-03")
                .build();

        CreatorSettlementResponseDto.CreatorSettlementAmountMonthly creatorSettlementAmountMonthly2 = CreatorSettlementResponseDto.CreatorSettlementAmountMonthly.builder()
                .channelId(1L)
                .creatorId(2L)
                .creatorName("test creator2")
                .settlementAmount(BigDecimal.valueOf(350))
                .settlementYearMonth("2022-03")
                .build();

        Mockito.when(salesManagementService.findMonthlyChannelSalesByChannelIdAndYearMonth(condition))
                .thenReturn(Collections.singletonList(channelSalesMonthly));

        Mockito.when(creatorSettlementService.findMonthlyCreatorSettlementByChannelId(creatorSettlementCondition))
                .thenReturn(Arrays.asList(creatorSettlementAmountMonthly, creatorSettlementAmountMonthly2));

        List<ChannelResponse.ChannelSalesAndCreatorSettlementAmount> result = channelFacade.searchSalesAndCreatorSettlement(channelId, request);

        ChannelResponse.ChannelSalesAndCreatorSettlementAmount channelResult = result.get(0);
        ChannelResponse.CreatorSettlementAmount creatorResult = channelResult.getCreators().get(0);
        ChannelResponse.CreatorSettlementAmount creatorResult2 = channelResult.getCreators().get(1);

        Assertions.assertThat(result.size()).isEqualTo(1);
        Assertions.assertThat(channelResult.getChannelId()).isEqualTo(1);
        Assertions.assertThat(channelResult.getChannelName()).isEqualTo("test channel");
        Assertions.assertThat(channelResult.getSearchMonth()).isEqualTo(YearMonth.parse("2022-03"));
        Assertions.assertThat(channelResult.getSalesAmount()).isEqualTo(BigDecimal.valueOf(1000));
        Assertions.assertThat(channelResult.getCreators().size()).isEqualTo(2);
        Assertions.assertThat(creatorResult.getCreatorId()).isEqualTo(1);
        Assertions.assertThat(creatorResult.getCreatorName()).isEqualTo("test creator");
        Assertions.assertThat(creatorResult.getSettlementAmount()).isEqualTo(BigDecimal.valueOf(350));

        Assertions.assertThat(creatorResult2.getCreatorId()).isEqualTo(2);
        Assertions.assertThat(creatorResult2.getCreatorName()).isEqualTo("test creator2");
        Assertions.assertThat(creatorResult2.getSettlementAmount()).isEqualTo(BigDecimal.valueOf(350));
    }
}