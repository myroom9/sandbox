package com.whahn.sandbox.domain.salesmanagement;

import com.whahn.sandbox.common.ErrorCode;
import com.whahn.sandbox.domain.salesmanagement.dto.request.SalesManagementRequestDto;
import com.whahn.sandbox.domain.salesmanagement.dto.response.SalesManagementResponseDto;
import com.whahn.sandbox.exception.cumtom.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.whahn.sandbox.domain.salesmanagement.dto.request.SalesManagementRequestDto.*;
import static com.whahn.sandbox.domain.salesmanagement.dto.response.SalesManagementResponseDto.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class SalesManagementService {
    private final SalesManagementRepository salesManagementRepository;

    public SalesManagement saveSalesManagement(SalesManagement salesManagement) {
        return Optional.of(salesManagementRepository.save(salesManagement))
                .orElseThrow(() -> new BusinessException(ErrorCode.SALES_MANAGEMENT_REGISTER_EXCEPTION));
    }

    public List<ChannelSalesMonthly> findMonthlyChannelSalesByChannelIdAndYearMonth(ChannelSalesMonthlyRequest condition) {
        return salesManagementRepository.findMonthlyChannelSalesByChannelIdAndYearMonth(condition);
    }
}
