package com.whahn.sandbox.domain.contract;

import com.whahn.sandbox.domain.channel.Channel;
import com.whahn.sandbox.domain.creator.Creator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    @Query("SELECT c FROM Contract c WHERE c.channel = :channel AND c.deletedAt IS NULL")
    List<Contract> findAllByChannel(Channel channel);
}
