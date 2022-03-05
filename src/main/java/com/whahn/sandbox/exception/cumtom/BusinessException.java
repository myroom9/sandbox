package com.whahn.sandbox.exception.cumtom;

import com.whahn.sandbox.common.ErrorCode;
import lombok.Getter;

public class BusinessException extends RuntimeException {
    @Getter
    private final ErrorCode errorCode;

    public BusinessException() {
        this(ErrorCode.INTERNAL_EXCEPTION);
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
