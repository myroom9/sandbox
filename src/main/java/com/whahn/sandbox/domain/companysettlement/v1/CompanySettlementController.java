package com.whahn.sandbox.domain.companysettlement.v1;

import com.whahn.sandbox.common.ApiResponse;
import com.whahn.sandbox.domain.companysettlement.CompanySettlementFacade;
import com.whahn.sandbox.domain.companysettlement.CompanySettlementService;
import com.whahn.sandbox.domain.companysettlement.v1.request.CompanySettlementRequest;
import com.whahn.sandbox.domain.companysettlement.v1.request.CompanySettlementRequest.CompanyTotalSalesAndSettlementMonthlyRequest;
import com.whahn.sandbox.domain.companysettlement.v1.response.CompanySettlementResponse;
import com.whahn.sandbox.domain.companysettlement.v1.response.CompanySettlementResponse.CompanyTotalSalesAndSettlement;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Tag(name = "회사 정산금 관련 API", description = "회사 정산금 관련 API LIST")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/company-settlement", produces = MediaType.APPLICATION_JSON_VALUE)
public class CompanySettlementController {
    private final CompanySettlementFacade companySettlementFacade;

    @Operation(summary = "[월별] 회사 총 수입 및 정산금액")
    @GetMapping("/monthly")
    public ApiResponse<List<CompanyTotalSalesAndSettlement>> searchCompanyTotalSalesAndSettlementAmount(@Valid CompanyTotalSalesAndSettlementMonthlyRequest request) {
        List<CompanyTotalSalesAndSettlement> response = companySettlementFacade.searchTotalSalesAndSettlementByMonthly(request);

        return ApiResponse.success(response);
    }
}
