package com.whahn.sandbox.domain.creatorsettlement;

import com.whahn.sandbox.domain.channel.Channel;
import com.whahn.sandbox.domain.creator.Creator;
import com.whahn.sandbox.domain.creatorsettlement.dto.request.CreatorSettlementRequestDto;
import com.whahn.sandbox.domain.creatorsettlement.dto.response.CreatorSettlementResponseDto;
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
import java.util.Collections;
import java.util.List;

import static com.whahn.sandbox.domain.creatorsettlement.dto.request.CreatorSettlementRequestDto.*;
import static com.whahn.sandbox.domain.creatorsettlement.dto.response.CreatorSettlementResponseDto.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreatorSettlementServiceTest {
    @Mock
    private CreatorSettlementRepository creatorSettlementRepository;

    @InjectMocks
    private CreatorSettlementService creatorSettlementService;

    @Test
    @DisplayName("[성공] 크리에이터 정산금 등록 테스트")
    void saveAllCreatorSettlementSuccessTest() {
        Channel channel = Channel.builder()
                .id(1L)
                .name("test channel")
                .subscriberCount(0)
                .build();
        Creator creator = Creator.builder()
                .id(1L)
                .birth("921102")
                .name("test")
                .sex(0)
                .build();

        CreatorSettlement creatorSettlement = CreatorSettlement.builder()
                .creator(creator)
                .channel(channel)
                .settlementAmount(BigDecimal.valueOf(1000))
                .settlementDate(LocalDate.of(2022, 3, 1))
                .build();

        Mockito.when(creatorSettlementRepository.saveAll(Collections.singletonList(creatorSettlement)))
                .thenReturn(Collections.singletonList(creatorSettlement));

        List<CreatorSettlement> result = creatorSettlementService.saveAllCreatorSettlement(Collections.singletonList(creatorSettlement));

        CreatorSettlement creatorSettlementResult = result.get(0);
        Assertions.assertThat(creatorSettlementResult.getCreator()).isEqualTo(creator);
        Assertions.assertThat(creatorSettlementResult.getChannel()).isEqualTo(channel);
        Assertions.assertThat(creatorSettlementResult.getSettlementDate()).isEqualTo(LocalDate.of(2022,3,1));
        Assertions.assertThat(creatorSettlementResult.getSettlementAmount()).isEqualTo(BigDecimal.valueOf(1000));
    }

    @Test
    @DisplayName("[성공] 채널 아이디에 따른 크리에이터 월별 정산금")
    void findMonthlyCreatorSettlementByChannelIdSuccessTest() {
        CreatorSettlementAmountMonthlyRequest request = CreatorSettlementAmountMonthlyRequest.builder()
                .channelId(1L)
                .searchStartMonth(LocalDate.of(2022, 3, 1))
                .searchEndMonth(LocalDate.of(2022, 3, 2))
                .build();

        CreatorSettlementAmountMonthly response = CreatorSettlementAmountMonthly.builder()
                .channelId(1L)
                .settlementYearMonth("2022-03")
                .creatorName("test")
                .settlementAmount(BigDecimal.valueOf(1000))
                .creatorId(1L)
                .build();

        Mockito.when(creatorSettlementRepository.findMonthlyCreatorSettlementAmountByChannelIdAndYearMonth(request))
                .thenReturn(Collections.singletonList(response));

        List<CreatorSettlementAmountMonthly> result = creatorSettlementService.findMonthlyCreatorSettlementByChannelId(request);

        CreatorSettlementAmountMonthly creatorSettlementAmountMonthly = result.get(0);
        Assertions.assertThat(result.size()).isEqualTo(1);
        Assertions.assertThat(creatorSettlementAmountMonthly.getCreatorId()).isEqualTo(1);
        Assertions.assertThat(creatorSettlementAmountMonthly.getChannelId()).isEqualTo(1);
        Assertions.assertThat(creatorSettlementAmountMonthly.getSettlementAmount()).isEqualTo(BigDecimal.valueOf(1000));
        Assertions.assertThat(creatorSettlementAmountMonthly.getSettlementYearMonth()).isEqualTo("2022-03");
    }

    @Test
    @DisplayName("[성공] 크리에이터 아이디별 월 정산 금액 조회 테스트")
    void findMonthlyCreatorSettlementByCreatorIdSuccessTest() {
        CreatorSettlementAmountMonthlyWithCreatorIdRequest request = CreatorSettlementAmountMonthlyWithCreatorIdRequest.builder()
                .creatorId(1L)
                .searchStartMonth(LocalDate.of(2022, 3, 1))
                .searchEndMonth(LocalDate.of(2022, 3, 1))
                .build();

        CreatorSettlementAmountWithCreatorIdMonthly response = CreatorSettlementAmountWithCreatorIdMonthly.builder()
                .creatorId(1L)
                .creatorName("test")
                .channelId(1L)
                .channelName("test channel")
                .settlementAmount(BigDecimal.valueOf(1000))
                .settlementYearMonth("2022-03")
                .build();

        Mockito.when(creatorSettlementRepository.findMonthlyCreatorSettlementAmountByCreatorIdAndYearMonth(request))
                .thenReturn(Collections.singletonList(response));

        List<CreatorSettlementAmountWithCreatorIdMonthly> result = creatorSettlementService.findMonthlyCreatorSettlementByCreatorId(request);
        System.out.println("result = " + result);

        CreatorSettlementAmountWithCreatorIdMonthly creatorSettlementAmountWithCreatorIdMonthly = result.get(0);
        Assertions.assertThat(creatorSettlementAmountWithCreatorIdMonthly.getCreatorId()).isEqualTo(1);
        Assertions.assertThat(creatorSettlementAmountWithCreatorIdMonthly.getChannelId()).isEqualTo(1);
        Assertions.assertThat(creatorSettlementAmountWithCreatorIdMonthly.getSettlementAmount()).isEqualTo(BigDecimal.valueOf(1000));
        Assertions.assertThat(creatorSettlementAmountWithCreatorIdMonthly.getSettlementYearMonth()).isEqualTo("2022-03");
    }
}