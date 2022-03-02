package com.whahn.sandbox.domain.channel;

import com.whahn.sandbox.domain.creator.Creator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {

}
