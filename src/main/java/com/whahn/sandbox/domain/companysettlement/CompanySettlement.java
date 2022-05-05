package com.whahn.sandbox.domain.companysettlement;

import com.whahn.sandbox.common.BaseEntity;
import com.whahn.sandbox.domain.channel.Channel;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@Table(name = "company_settlements")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CompanySettlement extends BaseEntity {

    public CompanySettlement(Channel channel, BigDecimal settlementAmount, LocalDate settlementDate) {
        this.channel = channel;
        this.settlementAmount = settlementAmount;
        this.settlementDate = settlementDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(1) UNSIGNED")
    private Long id;

    @ManyToOne(targetEntity = Channel.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id", updatable = false, nullable = false)
    private Channel channel;

    @Column(name = "settlement_amount")
    private BigDecimal settlementAmount;

    @Column(name = "settlement_date")
    private LocalDate settlementDate;
}
