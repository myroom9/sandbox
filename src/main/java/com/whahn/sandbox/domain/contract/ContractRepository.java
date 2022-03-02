package com.whahn.sandbox.domain.contract;

import com.whahn.sandbox.domain.creator.Creator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

}
