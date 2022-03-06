package com.whahn.sandbox.domain.contract;

import com.whahn.sandbox.domain.channel.Channel;
import com.whahn.sandbox.domain.channel.ChannelService;
import com.whahn.sandbox.domain.contract.v1.request.BasicContractRequest;
import com.whahn.sandbox.domain.creator.Creator;
import com.whahn.sandbox.domain.creator.CreatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ContractFacade {
    private final CreatorService creatorService;
    private final ChannelService channelService;
    private final ContractService contractService;

    @Transactional
    public void contractAndChannelSave(BasicContractRequest.Save request) {
        BasicContractRequest.ChannelRequest channelRequest = request.getChannelRequest();
        Channel saveChannel = channelRequest.toEntity(channelRequest);
        Channel channel = channelService.save(saveChannel);
        List<Creator> creators = creatorService.saveAll(request.getCreatorsRequest(), channel);
        contractService.saveAll(request.getContractRequest(), channel, creators);
    }
}
