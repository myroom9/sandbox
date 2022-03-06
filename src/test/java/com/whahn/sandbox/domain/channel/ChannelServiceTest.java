package com.whahn.sandbox.domain.channel;

import com.whahn.sandbox.common.ErrorCode;
import com.whahn.sandbox.domain.contract.v1.request.BasicContractRequest;
import com.whahn.sandbox.exception.cumtom.BusinessException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ChannelServiceTest {
    @Mock
    private ChannelRepository channelRepository;

    @InjectMocks
    private ChannelService channelService;

    @Test
    @DisplayName("[성공] 채널 등록 테스트")
    void saveSuccessTest() {
        Channel channel = new Channel("안원휘 테스트 채널");

        Mockito.when(channelRepository.save(channel))
                .thenReturn(channel);

        Channel result = channelService.save(channel);

        Assertions.assertThat(result.getName()).isEqualTo("안원휘 테스트 채널");
    }

    @Test
    @DisplayName("[성공] 채널 단일 조회 테스트")
    void findByIdSuccessTest() {
        Long channelId = 1L;

        Channel channel = Channel.builder()
                .id(1L)
                .name("test channel")
                .subscriberCount(0)
                .build();

        Mockito.when(channelRepository.findById(channelId))
                .thenReturn(Optional.of(channel));

        Channel result = channelService.findById(channelId);

        Assertions.assertThat(result.getId()).isEqualTo(channelId);
        Assertions.assertThat(result.getName()).isEqualTo("test channel");
        Assertions.assertThat(result.getSubscriberCount()).isEqualTo(0);
    }

    @Test
    @DisplayName("[예외] 채널 단일 조회 테스트")
    void findByIdExceptionTest() {
        Long channelId = 1L;

        Mockito.when(channelRepository.findById(channelId))
                .thenThrow(new BusinessException(ErrorCode.CHANNEL_NOT_EXCEPTION));

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            channelService.findById(channelId);
        });

        Assertions.assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.CHANNEL_NOT_EXCEPTION);
    }
}