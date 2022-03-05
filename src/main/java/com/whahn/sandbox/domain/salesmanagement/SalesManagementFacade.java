package com.whahn.sandbox.domain.salesmanagement;

import com.whahn.sandbox.domain.channel.Channel;
import com.whahn.sandbox.domain.channel.ChannelService;
import com.whahn.sandbox.domain.companysettlement.CompanySettlement;
import com.whahn.sandbox.domain.companysettlement.CompanySettlementService;
import com.whahn.sandbox.domain.contract.Contract;
import com.whahn.sandbox.domain.contract.ContractService;
import com.whahn.sandbox.domain.creatorsettlement.CreatorSettlement;
import com.whahn.sandbox.domain.creatorsettlement.CreatorSettlementService;
import com.whahn.sandbox.domain.salesmanagement.v1.request.BasicSalesManagementRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class SalesManagementFacade {

    private final ChannelService channelService;
    private final ContractService contractService;
    private final CompanySettlementService companySettlementService;
    private final SalesManagementService salesManagementService;
    private final CreatorSettlementService creatorSettlementService;

    // 수익금이 입력되는 순간 정산금액도 바로 입력하자
    @Transactional
    public void saveSalesAndSettlement(BasicSalesManagementRequest.SaveRequest request) {
        // 1. channel 데이터 구하기
        Channel channel = channelService.findById(request.getChannelId());

        // 2. creator 별로 contract 정보 구하기
        List<Contract> contracts = contractService.findAllByChannel(channel);

        // 3. 요율 따른 계산 후 회사 / 크리에이터 테이블 분리해야될듯?
        List<CreatorSettlement> creatorSettlements = contracts.stream().map(contract -> {
            BigDecimal creatorRate = BigDecimal.valueOf(contract.getCreatorRate()).divide(BigDecimal.valueOf(100));
            BigDecimal creatorSettlement = request.getSales().multiply(creatorRate);
            log.info("creatorSettlement:{}, creatorRate:{}, request.getSales:{}", creatorSettlement, creatorRate, request.getSales());
            return new CreatorSettlement(channel, contract.getCreator(), creatorSettlement, request.getSalesDate());
        }).collect(Collectors.toList());

        // 3-1. 회사는 channel id만 참조
        // 계산된 값으로 sales_management / settlement 1 , 2 저장
        SalesManagement salesManagement = new SalesManagement(request.getSales(), request.getSalesDate(), channel);
        salesManagementService.saveSalesManagement(salesManagement);
        creatorSettlementService.saveAllCreatorSettlement(creatorSettlements);

        BigDecimal creatorSettlementTotalAmount = creatorSettlements.stream()
                .map(CreatorSettlement::getSettlementAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal companySettlementAmount = request.getSales().subtract(creatorSettlementTotalAmount);
        CompanySettlement companySettlement = new CompanySettlement(channel, companySettlementAmount, request.getSalesDate());
        companySettlementService.saveCompanySettlement(companySettlement);

    }
}
