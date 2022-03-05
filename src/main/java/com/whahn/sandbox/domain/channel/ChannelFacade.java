package com.whahn.sandbox.domain.channel;

import com.whahn.sandbox.domain.channel.v1.request.ChannelRequest;
import com.whahn.sandbox.domain.channel.v1.response.ChannelResponse;
import com.whahn.sandbox.domain.salesmanagement.SalesManagementService;
import com.whahn.sandbox.domain.salesmanagement.dto.request.SalesManagementRequestDto;
import com.whahn.sandbox.domain.salesmanagement.dto.response.SalesManagementResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.YearMonth;
import java.util.List;

import static com.whahn.sandbox.domain.channel.v1.response.ChannelResponse.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChannelFacade {

    private final SalesManagementService salesManagementService;

    public List<ChannelSalesAndCreatorSettlementAmount> searchSalesAndCreatorSettlement(Long channelId, ChannelRequest.SearchSalesAmount request) {
        // 1. channel 수익금액 월별 조회
        SalesManagementRequestDto.ChannelSalesMonthlyRequest condition = new SalesManagementRequestDto.ChannelSalesMonthlyRequest(channelId, request.getSearchStartMonth().atDay(1), request.getSearchEndMonth().atEndOfMonth());

        List<SalesManagementResponseDto.ChannelSalesMonthly> channelSalesByMonthly = salesManagementService.findMonthlyChannelSalesByChannelIdAndYearMonth(condition);
        log.info("과연? {}", channelSalesByMonthly);
        // yearMonth는 string 이긴한데 일단 머지할꺼니... string도 괜찮을지도? ㅋㅋ

        // 2. creator별 정산금액 월별 조회
        

        // 3. merge


        return  null;
    }

}
