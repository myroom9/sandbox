package com.whahn.sandbox.domain.salesmanagement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SalesManagementFacade {
    // 수익금이 입력되는 순간 정산금액도 바로 입력하자
    public void saveSalesAndSettlement() {
        // 1. channel 데이터 구하기
        // 2. creator 별로 contract 정보 구하기
        // 3. 요율 따른 계산 후 회사 / 크리에이터 테이블 분리해야될듯?
        // 3-1. 회사는 channel id만 참조
        // 계산된 값으로 sales_management / settlement 1 , 2 저장
    }
}
