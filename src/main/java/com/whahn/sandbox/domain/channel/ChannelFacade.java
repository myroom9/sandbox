package com.whahn.sandbox.domain.channel;

import com.whahn.sandbox.domain.channel.v1.request.ChannelRequest;
import com.whahn.sandbox.domain.creatorsettlement.CreatorSettlementService;
import com.whahn.sandbox.domain.salesmanagement.SalesManagementService;
import com.whahn.sandbox.domain.salesmanagement.dto.request.SalesManagementRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import static com.whahn.sandbox.domain.channel.v1.response.ChannelResponse.*;
import static com.whahn.sandbox.domain.creatorsettlement.dto.request.CreatorSettlementRequestDto.*;
import static com.whahn.sandbox.domain.creatorsettlement.dto.response.CreatorSettlementResponseDto.*;
import static com.whahn.sandbox.domain.salesmanagement.dto.response.SalesManagementResponseDto.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChannelFacade {

    private final SalesManagementService salesManagementService;
    private final CreatorSettlementService creatorSettlementService;

    public List<ChannelSalesAndCreatorSettlementAmount> searchSalesAndCreatorSettlement(Long channelId, ChannelRequest.SearchSalesAmount request) {
        // 1. channel 수익금액 월별 조회
        SalesManagementRequestDto.ChannelSalesMonthlyRequest condition = new SalesManagementRequestDto.ChannelSalesMonthlyRequest(channelId, request.getSearchStartMonth().atDay(1), request.getSearchEndMonth().atEndOfMonth());

        List<ChannelSalesMonthly> channelSalesByMonthly = salesManagementService.findMonthlyChannelSalesByChannelIdAndYearMonth(condition);

        // 2. creator별 정산금액 월별 조회
        CreatorSettlementAmountMonthlyRequest creatorSettlementCondition = new CreatorSettlementAmountMonthlyRequest(channelId, request.getSearchStartMonth().atDay(1), request.getSearchEndMonth().atEndOfMonth());
        List<CreatorSettlementAmountMonthly> creatorSettlementAmountByMonthly = creatorSettlementService.findMonthlyCreatorSettlementByChannelId(creatorSettlementCondition);

        // 3. merge
        List<ChannelSalesAndCreatorSettlementAmount> result = new ArrayList<>();
        for(ChannelSalesMonthly a : channelSalesByMonthly) {
            List<CreatorSettlementAmount> creators = new ArrayList<>();
            for(CreatorSettlementAmountMonthly b : creatorSettlementAmountByMonthly) {
                if (a.getSalesYearMonth().equals(b.getSettlementYearMonth())) {
                    CreatorSettlementAmount creator = CreatorSettlementAmount.builder()
                            .creatorId(b.getCreatorId())
                            .creatorName(b.getCreatorName())
                            .settlementAmount(b.getSettlementAmount()).build();
                    creators.add(creator);
                }
            }
            ChannelSalesAndCreatorSettlementAmount merge = ChannelSalesAndCreatorSettlementAmount.builder()
                    .channelId(a.getChannelId())
                    .channelName(a.getChannelName())
                    .searchMonth(YearMonth.parse(a.getSalesYearMonth()))
                    .salesAmount(a.getSalesAmount())
                    .creators(creators).build();
            result.add(merge);
        }
        return  result;
    }

}
