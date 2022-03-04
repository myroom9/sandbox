package com.whahn.sandbox.common;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INTERNAL_EXCEPTION("50000000", "서비스 내부에서 알 수 없는 에러가 발생하였습니다."),

    // SETTLEMENT
    SETTLEMENT_REGISTER_EXCEPTION("50010100", "채널 수익금 등록에 실패하였습니다."),

    // CHANNEL
    CHANNEL_NOT_EXCEPTION("50020404", "채널 수익금 등록에 실패하였습니다."),
    ;

    private final String message;
    private final String code;

    ErrorCode(String code, String message) {
        this.message = message;
        this.code = code;
    }
}
