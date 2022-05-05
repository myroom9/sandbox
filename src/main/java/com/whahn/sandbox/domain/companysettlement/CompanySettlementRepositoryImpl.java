package com.whahn.sandbox.domain.companysettlement;

import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.JPQLQuery;
import com.whahn.sandbox.domain.channel.Channel;
import com.whahn.sandbox.domain.companysettlement.dto.request.CompanySettlementRequestDto;
import com.whahn.sandbox.domain.companysettlement.dto.request.CompanySettlementRequestDto.CompanySettlementMonthly;
import com.whahn.sandbox.domain.companysettlement.dto.response.CompanySettlementResponseDto;
import com.whahn.sandbox.domain.companysettlement.dto.response.CompanySettlementResponseDto.CompanySettlementMonthlyReturn;
import com.whahn.sandbox.domain.creatorsettlement.CreatorSettlement;
import com.whahn.sandbox.domain.creatorsettlement.dto.response.CreatorSettlementResponseDto;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.whahn.sandbox.common.QuerydslExpressionUtil.eq;
import static com.whahn.sandbox.domain.channel.QChannel.channel;
import static com.whahn.sandbox.domain.companysettlement.QCompanySettlement.companySettlement;
import static com.whahn.sandbox.domain.creator.QCreator.creator;
import static com.whahn.sandbox.domain.creatorsettlement.QCreatorSettlement.creatorSettlement;

public class CompanySettlementRepositoryImpl extends QuerydslRepositorySupport implements CompanySettlementRepositoryCustom {

    public CompanySettlementRepositoryImpl() {
        super(CompanySettlement.class);
    }

    public List<CompanySettlementMonthlyReturn> findCompanyMonthlySettlementAmountByChannelId(CompanySettlementMonthly condition) {
        StringTemplate dateFormat = Expressions.stringTemplate(
                "DATE_FORMAT({0}, {1})",
                companySettlement.settlementDate,
                ConstantImpl.create("%Y-%m")
        );

        JPQLQuery<CompanySettlement> query = from(companySettlement)
                .innerJoin(companySettlement.channel, channel)
                .where(
                        companySettlement.settlementDate.between(condition.getSearchStartMonth(), condition.getSearchEndMonth())
                )
                .groupBy(
                        dateFormat
                )
                ;

        return query.select(
                Projections.fields(CompanySettlementMonthlyReturn.class,
                        companySettlement.settlementAmount.sum().as("companySettlementAmount"),
                        dateFormat.as("settlementYearMonth"))).fetch();
    }
}
