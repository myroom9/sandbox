package com.whahn.sandbox.domain.contract;

import com.whahn.sandbox.domain.channel.Channel;
import com.whahn.sandbox.common.BaseEntity;
import com.whahn.sandbox.domain.creator.Creator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "contracts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Contract extends BaseEntity {

    public Contract(int creatorRate, int companyRate) {
        this.creatorRate = creatorRate;
        this.companyRate = companyRate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(1) UNSIGNED")
    private Long id;

    @ManyToOne(targetEntity = Creator.class, fetch = FetchType.LAZY)
    @JoinColumn(name="creator_id", updatable = false, nullable = false)
    private Creator creator;

    @ManyToOne(targetEntity = Channel.class, fetch = FetchType.LAZY)
    @JoinColumn(name="channel_id", updatable = false, nullable = false)
    private Channel channel;

    @Column(name = "creator_rate", nullable = false)
    private int creatorRate;

    @Column(name = "company_rate", nullable = false)
    private int companyRate;

    @Column(name = "status", length = 10, nullable = false, columnDefinition = "VARCHAR(10)")
    @Enumerated(EnumType.STRING)
    private ContractStatusType contractStatusType;

    @Override
    public String toString() {
        return ToStringBuilder
                .reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }

    public Contract addStatus() {
        this.contractStatusType = ContractStatusType.COMPLETE;
        return this;
    }

    public Contract addChannelAndCreator(Channel channel, Creator creator) {
        this.channel = channel;
        this.creator = creator;
        return this;
    }
}
