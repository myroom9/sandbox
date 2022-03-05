package com.whahn.sandbox.domain.companysettlement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanySettlementRepository extends JpaRepository<CompanySettlement, Long> {
}
