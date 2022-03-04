package com.whahn.sandbox.domain.salesmanagement;


import com.whahn.sandbox.common.BaseEntity;
import com.whahn.sandbox.domain.channel.Channel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Table(name = "sales_management")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SalesManagement extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(1) UNSIGNED")
    private Long id;

    @Column(name = "sales_amount", nullable = false)
    private BigDecimal salesAmount;

    @Column(name = "sales_date", nullable = false)
    private LocalDate salesDate;

    @ManyToOne(targetEntity = Channel.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id", updatable = false, nullable = false)
    private Channel channel;

    @Override
    public String toString() {
        return ToStringBuilder
                .reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
