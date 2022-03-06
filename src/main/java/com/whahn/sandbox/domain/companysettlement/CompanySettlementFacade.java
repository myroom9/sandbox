package com.whahn.sandbox.domain.companysettlement;

import com.whahn.sandbox.domain.companysettlement.dto.request.CompanySettlementRequestDto;
import com.whahn.sandbox.domain.companysettlement.dto.request.CompanySettlementRequestDto.CompanySettlementMonthly;
import com.whahn.sandbox.domain.companysettlement.dto.response.CompanySettlementResponseDto;
import com.whahn.sandbox.domain.companysettlement.v1.request.CompanySettlementRequest;
import com.whahn.sandbox.domain.companysettlement.v1.request.CompanySettlementRequest.CompanyTotalSalesAndSettlementMonthlyRequest;
import com.whahn.sandbox.domain.companysettlement.v1.response.CompanySettlementResponse;
import com.whahn.sandbox.domain.companysettlement.v1.response.CompanySettlementResponse.CompanyTotalSalesAndSettlement;
import com.whahn.sandbox.domain.salesmanagement.SalesManagementService;
import com.whahn.sandbox.domain.salesmanagement.dto.request.SalesManagementRequestDto;
import com.whahn.sandbox.domain.salesmanagement.dto.response.SalesManagementResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import static com.whahn.sandbox.domain.companysettlement.dto.response.CompanySettlementResponseDto.*;
import static com.whahn.sandbox.domain.salesmanagement.dto.request.SalesManagementRequestDto.*;
import static com.whahn.sandbox.domain.salesmanagement.dto.response.SalesManagementResponseDto.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class CompanySettlementFacade {
    private final CompanySettlementService companySettlementService;
    private final SalesManagementService salesManagementService;

    public List<CompanyTotalSalesAndSettlement> searchTotalSalesAndSettlementByMonthly(CompanyTotalSalesAndSettlementMonthlyRequest request) {
        CompanySettlementMonthly companySettlementMonthlyRequest = new CompanySettlementMonthly(request.getSearchStartMonth().atDay(1), request.getSearchEndMonth().atEndOfMonth());
        List<CompanySettlementMonthlyReturn> companySettlementMonthly = companySettlementService.findCompanySettlementMonthly(companySettlementMonthlyRequest);

        TotalSalesMonthlyRequest totalSalesMonthlyRequest = new TotalSalesMonthlyRequest(request.getSearchStartMonth().atDay(1), request.getSearchEndMonth().atEndOfMonth());
        List<TotalSalesMonthly> monthlyTotalSales = salesManagementService.findMonthlyTotalSalesByYearMonth(totalSalesMonthlyRequest);

        List<CompanyTotalSalesAndSettlement> result = new ArrayList<>();
        for(CompanySettlementMonthlyReturn a : companySettlementMonthly) {
            for(TotalSalesMonthly b : monthlyTotalSales) {
                if (a.getSettlementYearMonth().equals(b.getSalesYearMonth())) {
                    CompanyTotalSalesAndSettlement companyTotalSalesAndSettlement = new CompanyTotalSalesAndSettlement(b.getSalesAmount(), a.getCompanySettlementAmount(), YearMonth.parse(a.getSettlementYearMonth()));
                    result.add(companyTotalSalesAndSettlement);
                }
            }
        }

        return result;
    }
}
