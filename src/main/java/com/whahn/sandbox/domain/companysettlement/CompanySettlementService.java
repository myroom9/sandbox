package com.whahn.sandbox.domain.companysettlement;

import com.whahn.sandbox.common.ErrorCode;
import com.whahn.sandbox.exception.cumtom.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanySettlementService {
    private final CompanySettlementRepository companySettlementRepository;

    public CompanySettlement saveCompanySettlement(CompanySettlement companySettlement) {
        return Optional.of(companySettlementRepository.save(companySettlement))
                .orElseThrow(() -> new BusinessException(ErrorCode.COMPANY_SETTLEMENT_REGISTER_EXCEPTION));
    }
}
