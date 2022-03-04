package com.whahn.sandbox.exception.cumtom;

import com.whahn.sandbox.common.ErrorCode;
import lombok.Getter;

public class SettlementRegisterException extends RuntimeException {

    @Getter
    private final ErrorCode errorCode;

    public SettlementRegisterException() {
        this(ErrorCode.INTERNAL_EXCEPTION);
    }

    public SettlementRegisterException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }


}
