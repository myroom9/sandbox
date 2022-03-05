package com.whahn.sandbox.common;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INTERNAL_EXCEPTION("50000000", "서비스 내부에서 알 수 없는 에러가 발생하였습니다."),

    // SETTLEMENT (1XX)
    SETTLEMENT_REGISTER_EXCEPTION("50010100", "채널 수익금 등록에 실패하였습니다."),

    // CHANNEL (2XX)
    CHANNEL_NOT_EXCEPTION("50020404", "유튜브 채널 조회에 실패하였습니다."),

    // CONTRACT (3XX)
    CONTRACT_NOT_EXCEPTION("50030404", "계약 조회에 실패하였습니다."),

    // CREATOR_SETTLEMENT (4xx)
    CREATOR_SETTLEMENT_REGISTER_EXCEPTION("50040100", "유튜브 크리에이터 정산금 등록에 실패하였습니다."),

    // SALES_MANAGEMENT (5xx)
    SALES_MANAGEMENT_REGISTER_EXCEPTION("50050100", "유튜브 채널 수익금 등록에 실패하였습니다."),

    // COMPANY_SETTLEMENT (6xx)
    COMPANY_SETTLEMENT_REGISTER_EXCEPTION("50060100", "회사 정산금액 등록에 실패하였습니다."),
    ;

    private final String message;
    private final String code;

    ErrorCode(String code, String message) {
        this.message = message;
        this.code = code;
    }
}
