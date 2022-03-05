package com.whahn.sandbox.domain.creatorsettlement;

import com.whahn.sandbox.common.ErrorCode;
import com.whahn.sandbox.exception.cumtom.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreatorSettlementService {
    private final CreatorSettlementRepository creatorSettlementRepository;

    public List<CreatorSettlement> saveAllCreatorSettlement(List<CreatorSettlement> saveData) {
        return Optional.of(creatorSettlementRepository.saveAll(saveData))
                .orElseThrow(() -> new BusinessException(ErrorCode.CREATOR_SETTLEMENT_REGISTER_EXCEPTION));
    }
}
