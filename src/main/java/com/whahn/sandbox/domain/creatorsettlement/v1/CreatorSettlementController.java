package com.whahn.sandbox.domain.creatorsettlement.v1;

import com.whahn.sandbox.common.ApiResponse;
import com.whahn.sandbox.domain.creatorsettlement.CreatorSettlementFacade;
import com.whahn.sandbox.domain.creatorsettlement.v1.request.CreatorSettlementRequest;
import com.whahn.sandbox.domain.creatorsettlement.v1.response.CreatorSettlementResponse.CreatorMonthlySettlementAmountWithCreatorIdResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Tag(name = "크리에이터 정산 API", description = "크리에이터 정산관련 API LIST")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/creator-settlement", produces = MediaType.APPLICATION_JSON_VALUE)
public class CreatorSettlementController {
    private final CreatorSettlementFacade creatorSettlementFacade;

    @Operation(summary = "[월별] 크리에이터 기준 정산금액 조회")
    @GetMapping("/creator-id/{creatorId}/monthly")
    public ApiResponse<List<CreatorMonthlySettlementAmountWithCreatorIdResponse>> searchMonthlyCreatorSettlementByCreator(@Valid CreatorSettlementRequest.SearchMonthlySettlementByCreator request,
                                                                                                                          @PathVariable("creatorId") Long creatorId) {
        List<CreatorMonthlySettlementAmountWithCreatorIdResponse> response = creatorSettlementFacade.searchMonthlySettlementByCreatorId(creatorId, request);

        return ApiResponse.success(response);
    }
}
