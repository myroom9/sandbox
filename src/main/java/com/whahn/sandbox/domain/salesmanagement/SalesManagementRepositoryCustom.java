package com.whahn.sandbox.domain.salesmanagement;

import com.whahn.sandbox.domain.salesmanagement.dto.request.SalesManagementRequestDto;
import com.whahn.sandbox.domain.salesmanagement.dto.response.SalesManagementResponseDto;

import java.util.List;

import static com.whahn.sandbox.domain.salesmanagement.dto.request.SalesManagementRequestDto.*;
import static com.whahn.sandbox.domain.salesmanagement.dto.response.SalesManagementResponseDto.*;

public interface SalesManagementRepositoryCustom {
    List<ChannelSalesMonthly> findMonthlyChannelSalesByChannelIdAndYearMonth(ChannelSalesMonthlyRequest condition);

    List<TotalSalesMonthly> findMonthlyTotalSalesByYearMonth(TotalSalesMonthlyRequest condition);
}
