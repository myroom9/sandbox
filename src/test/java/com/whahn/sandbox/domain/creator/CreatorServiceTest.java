package com.whahn.sandbox.domain.creator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreatorServiceTest {
    @Mock
    private CreatorRepository creatorRepository;

    @InjectMocks
    private CreatorService creatorService;

    @Test
    @DisplayName("[성공] 크리에이터 저장 테스트")
    void saveAllSuccessTest() {
        Creator creator = Creator.builder()
                .id(1L)
                .birth("921102")
                .sex(0)
                .build();

        Mockito.when(creatorRepository.saveAll(Collections.singletonList(creator)))
                .thenReturn(Collections.singletonList(creator));

        List<Creator> creators = creatorService.saveAll(Collections.singletonList(creator));

        Creator creator1 = creators.get(0);
        Assertions.assertThat(creators.size()).isEqualTo(1);
        Assertions.assertThat(creator1.getBirth()).isEqualTo("921102");

    }
}