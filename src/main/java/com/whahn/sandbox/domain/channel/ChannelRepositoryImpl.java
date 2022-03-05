package com.whahn.sandbox.domain.channel;

import com.whahn.sandbox.domain.creator.Creator;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class ChannelRepositoryImpl extends QuerydslRepositorySupport implements ChannelRepositoryCustom {

    public ChannelRepositoryImpl() {
        super(Channel.class);
    }


}
