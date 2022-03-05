package com.whahn.sandbox.domain.salesmanagement;

import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.JPQLQuery;
import com.whahn.sandbox.domain.salesmanagement.dto.response.SalesManagementResponseDto;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.whahn.sandbox.common.QuerydslExpressionUtil.eq;
import static com.whahn.sandbox.domain.channel.QChannel.channel;
import static com.whahn.sandbox.domain.salesmanagement.QSalesManagement.salesManagement;
import static com.whahn.sandbox.domain.salesmanagement.dto.request.SalesManagementRequestDto.*;
import static com.whahn.sandbox.domain.salesmanagement.dto.response.SalesManagementResponseDto.*;

public class SalesManagementRepositoryImpl extends QuerydslRepositorySupport implements SalesManagementRepositoryCustom {

    public SalesManagementRepositoryImpl() {
        super(SalesManagement.class);
    }

    public List<ChannelSalesMonthly> findMonthlyChannelSalesByChannelIdAndYearMonth(ChannelSalesMonthlyRequest condition) {

        StringTemplate dateFormat = Expressions.stringTemplate(
                "DATE_FORMAT({0}, {1})"
                , salesManagement.salesDate
                , ConstantImpl.create("%Y-%m")
        );

        JPQLQuery<SalesManagement> query = from(salesManagement)
                .innerJoin(salesManagement.channel, channel)
                .where(
                        eq(salesManagement.channel.id, condition.getChannelId()),
                        salesManagement.salesDate.between(condition.getSearchStartMonth(), condition.getSearchEndMonth())
                )
                .groupBy(
                        dateFormat
                )
                ;

        return query.select(
                Projections.fields(ChannelSalesMonthly.class,
                        salesManagement.channel.id.as("channelId"),
                        salesManagement.channel.name.as("channelName"),
                        dateFormat.as("salesYearMonth"))).fetch();
    }
}
