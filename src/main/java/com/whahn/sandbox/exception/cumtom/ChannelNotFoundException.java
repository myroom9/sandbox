package com.whahn.sandbox.exception.cumtom;

import com.whahn.sandbox.common.ErrorCode;
import lombok.Getter;

public class ChannelNotFoundException extends RuntimeException {
    @Getter
    private final ErrorCode errorCode;

    public ChannelNotFoundException() {
        this(ErrorCode.INTERNAL_EXCEPTION);
    }

    public ChannelNotFoundException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
