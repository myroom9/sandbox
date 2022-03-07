package com.whahn.sandbox.domain.companysettlement.v1;

import com.whahn.sandbox.domain.channel.v1.ChannelController;
import com.whahn.sandbox.domain.companysettlement.CompanySettlementFacade;
import com.whahn.sandbox.domain.companysettlement.v1.request.CompanySettlementRequest;
import com.whahn.sandbox.domain.companysettlement.v1.response.CompanySettlementResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(
        value = { CompanySettlementController.class }
)
class CompanySettlementControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CompanySettlementFacade companySettlementFacade;

    @Test
    @DisplayName("[성공][월별] 회사 총 수입 및 정산금액 조회 테스트")
    void searchCompanyTotalSalesAndSettlementAmountSuccessTest() throws Exception {
        CompanySettlementResponse.CompanyTotalSalesAndSettlement result = CompanySettlementResponse.CompanyTotalSalesAndSettlement.builder()
                .settlementAmount(BigDecimal.valueOf(1000))
                .totalSales(BigDecimal.valueOf(1000))
                .targetYearMonth(YearMonth.of(2022, 3))
                .build();
        Mockito.when(companySettlementFacade.searchTotalSalesAndSettlementByMonthly(ArgumentMatchers.any()))
                .thenReturn(Collections.singletonList(result));

        mockMvc.perform(
                        get("/api/v1/company-settlement/monthly")
                                .param("searchStartMonth", "2022-02")
                                .param("searchEndMonth", "2022-03")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        // .andExpect(content().string())
        ;
    }
}