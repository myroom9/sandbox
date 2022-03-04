package com.whahn.sandbox.domain.settlement;

import com.whahn.sandbox.domain.settlement.v1.request.BasicSettlementRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SettlementFacade {
    private final SettlementService settlementService;

    public void saveDailySettlement(List<BasicSettlementRequest.SaveRequest> request) {
        settlementService.saveDailySaveAll(request);
    }
}
