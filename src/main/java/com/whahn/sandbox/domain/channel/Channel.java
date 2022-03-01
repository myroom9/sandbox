package com.whahn.sandbox.domain.channel;

import com.whahn.sandbox.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "channels")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Channel extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(1) UNSIGNED")
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "subscriber_count", nullable = false)
    private int subscriberCount;
}
