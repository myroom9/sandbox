package com.whahn.sandbox.domain.creatorsettlement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreatorSettlementRepository extends JpaRepository<CreatorSettlement, Long> {
}
