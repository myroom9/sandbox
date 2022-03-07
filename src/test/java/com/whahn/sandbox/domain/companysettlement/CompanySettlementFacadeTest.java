package com.whahn.sandbox.domain.companysettlement;

import com.whahn.sandbox.domain.companysettlement.dto.request.CompanySettlementRequestDto;
import com.whahn.sandbox.domain.companysettlement.dto.response.CompanySettlementResponseDto;
import com.whahn.sandbox.domain.companysettlement.v1.request.CompanySettlementRequest;
import com.whahn.sandbox.domain.companysettlement.v1.response.CompanySettlementResponse;
import com.whahn.sandbox.domain.salesmanagement.SalesManagementService;
import com.whahn.sandbox.domain.salesmanagement.dto.request.SalesManagementRequestDto;
import com.whahn.sandbox.domain.salesmanagement.dto.response.SalesManagementResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Collections;
import java.util.List;

import static com.whahn.sandbox.domain.companysettlement.dto.request.CompanySettlementRequestDto.*;
import static com.whahn.sandbox.domain.companysettlement.v1.request.CompanySettlementRequest.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CompanySettlementFacadeTest {

    @Mock
    private CompanySettlementService companySettlementService;

    @Mock
    private SalesManagementService salesManagementService;

    @InjectMocks
    private CompanySettlementFacade companySettlementFacade;

    @Test
    @DisplayName("[성공][월별] 회사 전체 금액 + 월별 정산 금액 조회")
    void searchTotalSalesAndSettlementByMonthlySuccessTest() {
        CompanyTotalSalesAndSettlementMonthlyRequest request = CompanyTotalSalesAndSettlementMonthlyRequest.builder()
                .searchStartMonth(YearMonth.of(2022, 3))
                .searchEndMonth(YearMonth.of(2022, 3))
                .build();

        CompanySettlementMonthly companySettlementMonthlyRequest = new CompanySettlementMonthly(request.getSearchStartMonth().atDay(1), request.getSearchEndMonth().atEndOfMonth());
        CompanySettlementResponseDto.CompanySettlementMonthlyReturn companySettlementMonthlyReturn = CompanySettlementResponseDto.CompanySettlementMonthlyReturn.builder()
                .settlementYearMonth("2022-03")
                .companySettlementAmount(BigDecimal.valueOf(1000))
                .build();

        Mockito.when(companySettlementService.findCompanySettlementMonthly(companySettlementMonthlyRequest))
                .thenReturn(Collections.singletonList(companySettlementMonthlyReturn));

        SalesManagementRequestDto.TotalSalesMonthlyRequest totalSalesMonthlyRequest = new SalesManagementRequestDto.TotalSalesMonthlyRequest(request.getSearchStartMonth().atDay(1), request.getSearchEndMonth().atEndOfMonth());
        SalesManagementResponseDto.TotalSalesMonthly monthlyTotalSales = SalesManagementResponseDto.TotalSalesMonthly.builder()
                .salesAmount(BigDecimal.valueOf(1000))
                .salesYearMonth("2022-03")
                .build();

        Mockito.when(salesManagementService.findMonthlyTotalSalesByYearMonth(totalSalesMonthlyRequest))
                .thenReturn(Collections.singletonList(monthlyTotalSales));

        List<CompanySettlementResponse.CompanyTotalSalesAndSettlement> result = companySettlementFacade.searchTotalSalesAndSettlementByMonthly(request);

        CompanySettlementResponse.CompanyTotalSalesAndSettlement response = result.get(0);
        Assertions.assertThat(response.getTotalSales()).isEqualTo(BigDecimal.valueOf(1000));
        Assertions.assertThat(response.getSettlementAmount()).isEqualTo(BigDecimal.valueOf(1000));
        Assertions.assertThat(response.getTargetYearMonth()).isEqualTo(YearMonth.of(2022,3));
    }
}