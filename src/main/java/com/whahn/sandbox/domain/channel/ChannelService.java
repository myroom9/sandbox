package com.whahn.sandbox.domain.channel;

import com.whahn.sandbox.common.ErrorCode;
import com.whahn.sandbox.domain.contract.v1.request.BasicContractRequest;
import com.whahn.sandbox.exception.cumtom.ChannelNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChannelService {
    private final ChannelRepository channelRepository;

    public Channel save(BasicContractRequest.ChannelRequest channelRequest) {
        Channel channel = channelRequest.toEntity(channelRequest);
        return channelRepository.save(channel);
    }

    public Channel findById(Long channelId) {
        return channelRepository.findById(channelId)
                .orElseThrow(() -> new ChannelNotFoundException(ErrorCode.CHANNEL_NOT_EXCEPTION));
    }
}
