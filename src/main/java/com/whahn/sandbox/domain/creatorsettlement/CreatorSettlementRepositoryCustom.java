package com.whahn.sandbox.domain.creatorsettlement;

import com.whahn.sandbox.domain.creatorsettlement.dto.request.CreatorSettlementRequestDto;
import com.whahn.sandbox.domain.creatorsettlement.dto.response.CreatorSettlementResponseDto;

import java.util.List;

public interface CreatorSettlementRepositoryCustom {
    List<CreatorSettlementResponseDto.CreatorSettlementAmountMonthly> findMonthlyCreatorSettlementAmountByChannelIdAndYearMonth(CreatorSettlementRequestDto.CreatorSettlementAmountMonthlyRequest condition);
}
