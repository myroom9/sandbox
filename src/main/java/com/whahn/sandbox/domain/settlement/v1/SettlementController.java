package com.whahn.sandbox.domain.settlement.v1;

import com.whahn.sandbox.common.ApiResponse;
import com.whahn.sandbox.domain.settlement.SettlementFacade;
import com.whahn.sandbox.domain.settlement.v1.request.BasicSettlementRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Tag(name = "정산 API", description = "정산 API LIST")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/settlement", produces = MediaType.APPLICATION_JSON_VALUE)
public class SettlementController {

    private final SettlementFacade settlementFacade;

    @Operation(summary = "유튜브 수익금액 일별 데이터 등록 API")
    @PostMapping("/daily")
    public ApiResponse<Void> saveDailySettlement(@Valid @RequestBody List<BasicSettlementRequest.SaveRequest> request) {
        log.info("유튜브 수익금액 일별 데이터 등록 요청 데이터: {}", request);
        settlementFacade.saveDailySettlement(request);

        return ApiResponse.success();
    }
}
