package com.whahn.sandbox.domain.creatorsettlement;

import com.whahn.sandbox.common.ErrorCode;
import com.whahn.sandbox.exception.cumtom.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.whahn.sandbox.domain.creatorsettlement.dto.request.CreatorSettlementRequestDto.*;
import static com.whahn.sandbox.domain.creatorsettlement.dto.response.CreatorSettlementResponseDto.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreatorSettlementService {
    private final CreatorSettlementRepository creatorSettlementRepository;

    public List<CreatorSettlement> saveAllCreatorSettlement(List<CreatorSettlement> saveData) {
        return Optional.of(creatorSettlementRepository.saveAll(saveData))
                .orElseThrow(() -> new BusinessException(ErrorCode.CREATOR_SETTLEMENT_REGISTER_EXCEPTION));
    }

    @Transactional(readOnly = true)
    public List<CreatorSettlementAmountMonthly> findMonthlyCreatorSettlementByChannelId(CreatorSettlementAmountMonthlyRequest request) {
        return creatorSettlementRepository.findMonthlyCreatorSettlementAmountByChannelIdAndYearMonth(request);
    }

    @Transactional(readOnly = true)
    public List<CreatorSettlementAmountWithCreatorIdMonthly> findMonthlyCreatorSettlementByCreatorId(CreatorSettlementAmountMonthlyWithCreatorIdRequest request) {
        return creatorSettlementRepository.findMonthlyCreatorSettlementAmountByCreatorIdAndYearMonth(request);
    }
}
