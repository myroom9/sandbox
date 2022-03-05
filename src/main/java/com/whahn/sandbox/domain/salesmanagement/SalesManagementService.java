package com.whahn.sandbox.domain.salesmanagement;

import com.whahn.sandbox.common.ErrorCode;
import com.whahn.sandbox.exception.cumtom.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SalesManagementService {
    private final SalesManagementRepository salesManagementRepository;

    public SalesManagement saveSalesManagement(SalesManagement salesManagement) {
        return Optional.of(salesManagementRepository.save(salesManagement))
                .orElseThrow(() -> new BusinessException(ErrorCode.SALES_MANAGEMENT_REGISTER_EXCEPTION));
    }
}
