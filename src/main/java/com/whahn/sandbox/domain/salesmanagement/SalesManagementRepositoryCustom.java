package com.whahn.sandbox.domain.salesmanagement;

import com.whahn.sandbox.domain.salesmanagement.dto.request.SalesManagementRequestDto;
import com.whahn.sandbox.domain.salesmanagement.dto.response.SalesManagementResponseDto;

import java.util.List;

public interface SalesManagementRepositoryCustom {
    List<SalesManagementResponseDto.ChannelSalesMonthly> findMonthlyChannelSalesByChannelIdAndYearMonth(SalesManagementRequestDto.ChannelSalesMonthlyRequest condition);
}
