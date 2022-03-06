package com.whahn.sandbox.domain.channel.v1;

import com.whahn.sandbox.domain.channel.ChannelFacade;
import com.whahn.sandbox.domain.channel.v1.request.ChannelRequest;
import com.whahn.sandbox.domain.channel.v1.response.ChannelResponse;
import com.whahn.sandbox.domain.creator.Creator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(
        value = { ChannelController.class }
)
class ChannelControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ChannelFacade channelFacade;

    @Test
    @DisplayName("[성공][월별] 유튜브 채널 수익금 조회 및 크리에이터별 정산금액 조회")
    void searchSalesAmountAndCreatorSettleAmountSuccessTest() throws Exception {
        ChannelRequest.SearchSalesAmount request = ChannelRequest.SearchSalesAmount.builder()
                .searchStartMonth(YearMonth.parse("2022-02"))
                .searchEndMonth(YearMonth.parse("2022-03"))
                .build();

        ChannelResponse.CreatorSettlementAmount creatorSettlementAmount = ChannelResponse.CreatorSettlementAmount.builder()
                .creatorId(1L)
                .creatorName("whahn")
                .settlementAmount(BigDecimal.valueOf(1000))
                .build();

        ChannelResponse.ChannelSalesAndCreatorSettlementAmount result = ChannelResponse.ChannelSalesAndCreatorSettlementAmount.builder()
                .salesAmount(BigDecimal.valueOf(1000))
                .channelId(1L)
                .channelName("test channel")
                .searchMonth(YearMonth.parse("2022-02"))
                .creators(List.of(creatorSettlementAmount))
                .build();


        Mockito.when(channelFacade.searchSalesAndCreatorSettlement(1L, request))
                        .thenReturn(List.of(result));

        mockMvc.perform(
                get("/api/v1/channel/1/monthly/sales")
                        .param("searchStartMonth", "2022-02")
                        .param("searchEndMonth", "2022-03")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // .andExpect(content().string())
        ;
    }
}