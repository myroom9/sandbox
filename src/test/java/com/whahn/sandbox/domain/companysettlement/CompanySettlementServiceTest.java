package com.whahn.sandbox.domain.companysettlement;

import com.whahn.sandbox.common.ErrorCode;
import com.whahn.sandbox.domain.channel.Channel;
import com.whahn.sandbox.domain.companysettlement.dto.request.CompanySettlementRequestDto;
import com.whahn.sandbox.domain.companysettlement.dto.response.CompanySettlementResponseDto;
import com.whahn.sandbox.exception.cumtom.BusinessException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CompanySettlementServiceTest {
    @Mock
    private CompanySettlementRepository companySettlementRepository;

    @InjectMocks
    private CompanySettlementService companySettlementService;

    @Test
    @DisplayName("[성공] 회사 정산금 등록 테스트")
    void saveCompanySettlementSuccessTest() {
        Channel channel = Channel.builder()
                .id(1L)
                .name("test channel")
                .subscriberCount(0)
                .build();

        CompanySettlement companySettlement = CompanySettlement.builder()
                .channel(channel)
                .settlementAmount(BigDecimal.valueOf(1000))
                .settlementDate(LocalDate.of(2022, 2, 3))
                .build();

        CompanySettlement companySettlementResult = CompanySettlement.builder()
                .channel(channel)
                .id(1L)
                .settlementAmount(BigDecimal.valueOf(1000))
                .settlementDate(LocalDate.of(2022, 2, 3))
                .build();

        Mockito.when(companySettlementRepository.save(companySettlement))
                .thenReturn(companySettlementResult);

        CompanySettlement companySettlementReturn = companySettlementService.saveCompanySettlement(companySettlement);

        Assertions.assertThat(companySettlementReturn.getId()).isEqualTo(1);
        Assertions.assertThat(companySettlementReturn.getSettlementAmount()).isEqualTo(BigDecimal.valueOf(1000));
        Assertions.assertThat(companySettlementReturn.getSettlementDate()).isEqualTo(LocalDate.of(2022,2,3));
    }

    @Test
    @DisplayName("[예외] 회사 정산금 등록 테스트")
    void saveCompanySettlementExceptionTest() {
        Mockito.when(companySettlementRepository.save(ArgumentMatchers.any()))
                .thenReturn(null);

        assertThrows(NullPointerException.class, () -> {
            companySettlementService.saveCompanySettlement(new CompanySettlement());
        });
    }

    @Test
    @DisplayName("[예외2] 회사 정산금 등록 테스트")
    void saveCompanySettlementExceptionTest2() {
        Mockito.when(companySettlementRepository.save(ArgumentMatchers.any()))
                .thenThrow(new BusinessException(ErrorCode.COMPANY_SETTLEMENT_REGISTER_EXCEPTION));

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            companySettlementService.saveCompanySettlement(new CompanySettlement());
        });

        Assertions.assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.COMPANY_SETTLEMENT_REGISTER_EXCEPTION);
    }

    @Test
    @DisplayName("[성공][월별] 회사 정산 금액 조회 테스트")
    void findCompanySettlementMonthlySuccessTest() {
        CompanySettlementRequestDto.CompanySettlementMonthly request = CompanySettlementRequestDto.CompanySettlementMonthly.builder()
                .searchStartMonth(LocalDate.of(2022, 3, 2))
                .searchEndMonth(LocalDate.of(2022, 3, 3))
                .build();

        CompanySettlementResponseDto.CompanySettlementMonthlyReturn response = CompanySettlementResponseDto.CompanySettlementMonthlyReturn.builder()
                .companySettlementAmount(BigDecimal.valueOf(1000))
                .settlementYearMonth("2022-03")
                .build();

        Mockito.when(companySettlementRepository.findCompanyMonthlySettlementAmountByChannelId(request))
                .thenReturn(Collections.singletonList(response));

        List<CompanySettlementResponseDto.CompanySettlementMonthlyReturn> result = companySettlementService.findCompanySettlementMonthly(request);

        CompanySettlementResponseDto.CompanySettlementMonthlyReturn companySettlementMonthly = result.get(0);
        Assertions.assertThat(companySettlementMonthly.getCompanySettlementAmount()).isEqualTo(BigDecimal.valueOf(1000));
        Assertions.assertThat(companySettlementMonthly.getSettlementYearMonth()).isEqualTo("2022-03");
    }
}