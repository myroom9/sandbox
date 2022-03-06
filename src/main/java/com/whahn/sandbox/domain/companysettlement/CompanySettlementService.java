package com.whahn.sandbox.domain.companysettlement;

import com.whahn.sandbox.common.ErrorCode;
import com.whahn.sandbox.domain.companysettlement.dto.request.CompanySettlementRequestDto;
import com.whahn.sandbox.domain.companysettlement.dto.request.CompanySettlementRequestDto.CompanySettlementMonthly;
import com.whahn.sandbox.domain.companysettlement.dto.response.CompanySettlementResponseDto;
import com.whahn.sandbox.domain.companysettlement.v1.request.CompanySettlementRequest;
import com.whahn.sandbox.exception.cumtom.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.whahn.sandbox.domain.companysettlement.dto.response.CompanySettlementResponseDto.*;
import static com.whahn.sandbox.domain.companysettlement.v1.request.CompanySettlementRequest.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanySettlementService {
    private final CompanySettlementRepository companySettlementRepository;

    public CompanySettlement saveCompanySettlement(CompanySettlement companySettlement) {
        return Optional.of(companySettlementRepository.save(companySettlement))
                .orElseThrow(() -> new BusinessException(ErrorCode.COMPANY_SETTLEMENT_REGISTER_EXCEPTION));
    }

    @Transactional(readOnly = true)
    public List<CompanySettlementMonthlyReturn> findCompanySettlementMonthly(CompanySettlementMonthly request) {
        return companySettlementRepository.findCompanyMonthlySettlementAmountByChannelId(request);
    }
}
