package com.whahn.sandbox.domain.contract;

import lombok.Getter;

@Getter
public enum ContractStatusType {
    WAIT("WAIT"),
    COMPLETE("COMPLETE"),
    ;

    private final String status;

    ContractStatusType(String status) {
        this.status = status;
    }
}
