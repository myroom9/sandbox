package com.whahn.sandbox.domain.channel;

import com.whahn.sandbox.common.BaseEntity;
import com.whahn.sandbox.common.ModelMapperUtil;
import com.whahn.sandbox.domain.contract.v1.request.BasicContractRequest;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "channels")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Channel extends BaseEntity {

    public Channel(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(1) UNSIGNED")
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "subscriber_count", nullable = false)
    private int subscriberCount;

    @Override
    public String toString() {
        return ToStringBuilder
                .reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
