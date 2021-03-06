package com.whahn.sandbox.domain.creatorsettlement;

import com.whahn.sandbox.domain.creatorsettlement.dto.request.CreatorSettlementRequestDto;
import com.whahn.sandbox.domain.creatorsettlement.dto.request.CreatorSettlementRequestDto.CreatorSettlementAmountMonthlyRequest;
import com.whahn.sandbox.domain.creatorsettlement.dto.request.CreatorSettlementRequestDto.CreatorSettlementAmountMonthlyWithCreatorIdRequest;
import com.whahn.sandbox.domain.creatorsettlement.dto.response.CreatorSettlementResponseDto;
import com.whahn.sandbox.domain.creatorsettlement.dto.response.CreatorSettlementResponseDto.CreatorSettlementAmountMonthly;
import com.whahn.sandbox.domain.creatorsettlement.dto.response.CreatorSettlementResponseDto.CreatorSettlementAmountWithCreatorIdMonthly;

import java.util.List;

public interface CreatorSettlementRepositoryCustom {
    List<CreatorSettlementAmountMonthly> findMonthlyCreatorSettlementAmountByChannelIdAndYearMonth(CreatorSettlementAmountMonthlyRequest condition);

    List<CreatorSettlementAmountWithCreatorIdMonthly> findMonthlyCreatorSettlementAmountByCreatorIdAndYearMonth(CreatorSettlementAmountMonthlyWithCreatorIdRequest condition);
}
