package com.whahn.sandbox.domain.salesmanagement.v1;

import com.whahn.sandbox.common.ApiResponse;
import com.whahn.sandbox.domain.salesmanagement.SalesManagementFacade;
import com.whahn.sandbox.domain.salesmanagement.v1.request.BasicSalesManagementRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@Tag(name = "매출 API", description = "매출 API LIST")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/sales-management", produces = MediaType.APPLICATION_JSON_VALUE)
public class SalesManagementController {

    private SalesManagementFacade salesManagementFacade;

    @Operation(summary = "유튜브 채널별 수익 금액 일별 데이터 등록 API")
    @PostMapping("/daily")
    public ApiResponse<Void> saveDailySales(@Valid BasicSalesManagementRequest.SaveRequest request) {

        return ApiResponse.success();
    }

}
