package com.whahn.sandbox.domain.creator;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class CreatorRepositoryImpl extends QuerydslRepositorySupport implements CreatorRepositoryCustom {

    public CreatorRepositoryImpl() {
        super(Creator.class);
    }


}
