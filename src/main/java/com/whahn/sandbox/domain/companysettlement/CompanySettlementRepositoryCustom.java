package com.whahn.sandbox.domain.companysettlement;

import com.whahn.sandbox.domain.companysettlement.dto.request.CompanySettlementRequestDto;
import com.whahn.sandbox.domain.companysettlement.dto.response.CompanySettlementResponseDto;

import java.util.List;

public interface CompanySettlementRepositoryCustom {
    List<CompanySettlementResponseDto.CompanySettlementMonthlyReturn> findCompanyMonthlySettlementAmountByChannelId(CompanySettlementRequestDto.CompanySettlementMonthly condition);
}
