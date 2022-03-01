package com.whahn.sandbox.domain.creatorgrade;

import com.whahn.sandbox.common.BaseEntity;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class CreatorGrade extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(1) UNSIGNED")
    private Long id;

    @Column(name = "grade", length = 10, nullable = false, columnDefinition = "VARCHAR(10)")
    @Enumerated(EnumType.STRING)
    private CreatorGradeType creatorGradeType;

    @Column(nullable = false)
    private int discount;
}
