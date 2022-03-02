package com.whahn.sandbox.domain.channel;

import com.whahn.sandbox.domain.contract.v1.request.BasicContractRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChannelService {
    private final ChannelRepository channelRepository;

    public Channel save(BasicContractRequest.ChannelRequest channelRequest) {
        Channel channel = channelRequest.toEntity(channelRequest);
        return channelRepository.save(channel);
    }
}
