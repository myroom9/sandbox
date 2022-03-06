package com.whahn.sandbox.domain.creatorsettlement;

import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.JPQLQuery;
import com.whahn.sandbox.domain.salesmanagement.SalesManagementRepositoryCustom;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.whahn.sandbox.common.QuerydslExpressionUtil.eq;
import static com.whahn.sandbox.domain.channel.QChannel.channel;
import static com.whahn.sandbox.domain.creator.QCreator.creator;
import static com.whahn.sandbox.domain.creatorsettlement.QCreatorSettlement.creatorSettlement;
import static com.whahn.sandbox.domain.creatorsettlement.dto.request.CreatorSettlementRequestDto.*;
import static com.whahn.sandbox.domain.creatorsettlement.dto.response.CreatorSettlementResponseDto.*;

public class CreatorSettlementRepositoryImpl extends QuerydslRepositorySupport implements CreatorSettlementRepositoryCustom {

    public CreatorSettlementRepositoryImpl() {
        super(CreatorSettlement.class);
    }

    public List<CreatorSettlementAmountMonthly> findMonthlyCreatorSettlementAmountByChannelIdAndYearMonth(CreatorSettlementAmountMonthlyRequest condition) {

        StringTemplate dateFormat = Expressions.stringTemplate(
                "DATE_FORMAT({0}, {1})"
                , creatorSettlement.settlementDate
                , ConstantImpl.create("%Y-%m")
        );

        JPQLQuery<CreatorSettlement> query = from(creatorSettlement)
                .innerJoin(creatorSettlement.channel, channel)
                .innerJoin(creatorSettlement.creator, creator)
                .where(
                        eq(creatorSettlement.channel.id, condition.getChannelId()),
                        creatorSettlement.settlementDate.between(condition.getSearchStartMonth(), condition.getSearchEndMonth())
                )
                .groupBy(
                        dateFormat,
                        creatorSettlement.creator.id
                )
                ;

        return query.select(
                Projections.fields(CreatorSettlementAmountMonthly.class,
                        creatorSettlement.channel.id.as("channelId"),
                        creatorSettlement.creator.id.as("creatorId"),
                        creatorSettlement.creator.name.as("creatorName"),
                        creatorSettlement.settlementAmount.sum().as("settlementAmount"),
                        dateFormat.as("settlementYearMonth"))).fetch();
    }

    public List<CreatorSettlementAmountWithCreatorIdMonthly> findMonthlyCreatorSettlementAmountByCreatorIdAndYearMonth(CreatorSettlementAmountMonthlyWithCreatorIdRequest condition) {

        StringTemplate dateFormat = Expressions.stringTemplate(
                "DATE_FORMAT({0}, {1})"
                , creatorSettlement.settlementDate
                , ConstantImpl.create("%Y-%m")
        );

        JPQLQuery<CreatorSettlement> query = from(creatorSettlement)
                .innerJoin(creatorSettlement.channel, channel)
                .innerJoin(creatorSettlement.creator, creator)
                .where(
                        eq(creatorSettlement.creator.id, condition.getCreatorId()),
                        creatorSettlement.settlementDate.between(condition.getSearchStartMonth(), condition.getSearchEndMonth())
                )
                .groupBy(
                        dateFormat,
                        creatorSettlement.creator.id
                )
                ;

        return query.select(
                Projections.fields(CreatorSettlementAmountWithCreatorIdMonthly.class,
                        creatorSettlement.channel.id.as("channelId"),
                        creatorSettlement.channel.name.as("channelName"),
                        creatorSettlement.creator.id.as("creatorId"),
                        creatorSettlement.creator.name.as("creatorName"),
                        creatorSettlement.settlementAmount.sum().as("settlementAmount"),
                        dateFormat.as("settlementYearMonth"))).fetch();
    }
}
