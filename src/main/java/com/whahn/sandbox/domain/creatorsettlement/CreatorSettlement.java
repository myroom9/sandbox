package com.whahn.sandbox.domain.creatorsettlement;

import com.whahn.sandbox.common.BaseEntity;
import com.whahn.sandbox.domain.channel.Channel;
import com.whahn.sandbox.domain.creator.Creator;
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
@Table(name = "creator_settlements")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreatorSettlement extends BaseEntity {

    public CreatorSettlement(Channel channel,
                             Creator creator,
                             BigDecimal settlementAmount,
                             LocalDate settlementDate) {
        this.channel = channel;
        this.creator = creator;
        this.settlementAmount = settlementAmount;
        this.settlementDate = settlementDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(1)  UNSIGNED")
    private Long id;

    @Column(name = "settlement_amount", nullable = false)
    private BigDecimal settlementAmount;

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
