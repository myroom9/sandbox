package com.whahn.sandbox.domain.channel;

import com.whahn.sandbox.common.ErrorCode;
import com.whahn.sandbox.exception.cumtom.BusinessException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// @TestPropertySource(properties = {"spring.jpa.properties.hibernate.hbm2ddl.auto=create"})
class ChannelRepositoryTest {
    @Autowired
    private ChannelRepository channelRepository;

    @BeforeEach
    void init() {
        Channel channel = Channel.builder().name("test channel").build();
        channelRepository.save(channel);
    }

    @Test
    @DisplayName("[성공] 유튜브 채널 등록 성공 테스트")
    void saveSuccessTest() {
        Channel channel = Channel.builder().name("test channel").build();
        Channel result = channelRepository.save(channel);

        Assertions.assertThat(result).isInstanceOf(Channel.class);
        Assertions.assertThat(result.getName()).isEqualTo("test channel");
        Assertions.assertThat(result.getSubscriberCount()).isEqualTo(0);
    }

    @Test
    @DisplayName("[성공] 유튜브 채널 조회 성공 테스트")
    void findByIdSuccessTest() {
        Long channelId = 1L;

        Channel channel = channelRepository.findById(channelId)
                .orElseThrow(() -> new BusinessException(ErrorCode.CHANNEL_NOT_EXCEPTION));

        Assertions.assertThat(channel).isInstanceOf(Channel.class);
    }
}