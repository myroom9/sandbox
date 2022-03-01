package com.whahn.sandbox.domain.creatorgrade;

import lombok.Getter;

@Getter
public enum CreatorGradeType {
    NORMAL("NORMAL"),
    VIP("VIP"),
    SVIP("SVIP"),
    ;

    private final String grade;

    CreatorGradeType(String grade) {
        this.grade = grade;
    }
}
