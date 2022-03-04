package com.whahn.sandbox.domain.settlement;

import com.whahn.sandbox.common.BaseEntity;
import com.whahn.sandbox.domain.channel.Channel;
import com.whahn.sandbox.domain.creator.Creator;
import com.whahn.sandbox.domain.settlement.v1.request.BasicSettlementRequest;
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
@Table(name = "settlements")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Settlement extends BaseEntity {

    public Settlement(BasicSettlementRequest.SaveRequest request, Channel channel) {
        this.creatorSettlementAmount = request.getCreatorSettlementAmount();
        this.companySettlementAmount = request.getCompanySettlementAmount();
        this.settlementDate = request.getSettlementDate();
        this.channel = channel;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(1)  UNSIGNED")
    private Long id;

    @Column(name = "creator_settlement_amount", nullable = false)
    private BigDecimal creatorSettlementAmount;

    @Column(name = "company_settlement_amount", nullable = false)
    private BigDecimal companySettlementAmount;

    @Column(name = "settlement_date", nullable = false)
    private LocalDate settlementDate;

    @ManyToOne(targetEntity = Channel.class, fetch = FetchType.LAZY)
    @JoinColumn(name="channel_id", updatable = false, nullable = false)
    private Channel channel;

    @ManyToOne(targetEntity = Creator.class, fetch = FetchType.LAZY)
    @JoinColumn(name="creator_id", updatable = false, nullable = false)
    private Creator creator;

    @Override
    public String toString() {
        return ToStringBuilder
                .reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
