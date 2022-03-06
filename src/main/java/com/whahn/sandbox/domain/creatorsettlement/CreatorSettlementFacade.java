package com.whahn.sandbox.domain.creatorsettlement;

import com.whahn.sandbox.domain.creatorsettlement.dto.request.CreatorSettlementRequestDto;
import com.whahn.sandbox.domain.creatorsettlement.dto.response.CreatorSettlementResponseDto;
import com.whahn.sandbox.domain.creatorsettlement.dto.response.CreatorSettlementResponseDto.CreatorSettlementAmountWithCreatorIdMonthly;
import com.whahn.sandbox.domain.creatorsettlement.v1.CreatorSettlementRequest;
import com.whahn.sandbox.domain.creatorsettlement.v1.CreatorSettlementRequest.SearchMonthlySettlementByCreator;
import com.whahn.sandbox.domain.creatorsettlement.v1.response.CreatorSettlementResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.whahn.sandbox.domain.creatorsettlement.v1.response.CreatorSettlementResponse.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreatorSettlementFacade {
    private final CreatorSettlementService creatorSettlementService;

    public List<CreatorMonthlySettlementAmountWithCreatorIdResponse> searchMonthlySettlementByCreatorId(Long creatorId, SearchMonthlySettlementByCreator request) {
        CreatorSettlementRequestDto.CreatorSettlementAmountMonthlyWithCreatorIdRequest condition = CreatorSettlementRequestDto.CreatorSettlementAmountMonthlyWithCreatorIdRequest.builder()
                .creatorId(creatorId)
                .searchStartMonth(request.getSearchStartMonth().atDay(1))
                .searchEndMonth(request.getSearchEndMonth().atEndOfMonth()).build();

        List<CreatorSettlementAmountWithCreatorIdMonthly> monthlyCreatorSettlement = creatorSettlementService.findMonthlyCreatorSettlementByCreatorId(condition);
        List<CreatorMonthlySettlementAmountWithCreatorIdResponse> result = monthlyCreatorSettlement.stream()
                .map(o -> new CreatorMonthlySettlementAmountWithCreatorIdResponse().toEntity(o))
                .collect(Collectors.toList());

        return result;
    }
}
