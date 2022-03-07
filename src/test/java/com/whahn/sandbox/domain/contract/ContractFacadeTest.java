package com.whahn.sandbox.domain.contract;

import com.whahn.sandbox.domain.channel.Channel;
import com.whahn.sandbox.domain.channel.ChannelService;
import com.whahn.sandbox.domain.contract.v1.request.BasicContractRequest;
import com.whahn.sandbox.domain.creator.Creator;
import com.whahn.sandbox.domain.creator.CreatorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContractFacadeTest {
    @Mock
    private CreatorService creatorService;
    @Mock
    private ChannelService channelService;
    @Mock
    private ContractService contractService;
    @InjectMocks
    private ContractFacade contractFacade;

    @Test
    @DisplayName("[성공] 계약 및 채널, 크리에이터 저장 테스트")
    void contractAndChannelSaveSuccessTest() {
        BasicContractRequest.ChannelRequest channelRequest = BasicContractRequest.ChannelRequest
                .builder()
                .name("test channel")
                .build();

        BasicContractRequest.CreatorRequest creatorRequest = BasicContractRequest.CreatorRequest.builder()
                .name("test")
                .birth("921102")
                .sex(0)
                .build();

        BasicContractRequest.ContractRequest contractRequest = BasicContractRequest.ContractRequest.builder()
                .companyRate(30)
                .creatorRate(70)
                .build();

        BasicContractRequest.Save request = BasicContractRequest.Save.builder()
                .channelRequest(channelRequest)
                .creatorsRequest(Collections.singletonList(creatorRequest))
                .contractRequest(contractRequest)
                .build();

        Channel channel = Channel.builder()
                .id(1L)
                .name("test channel")
                .subscriberCount(0)
                .build();
        Creator creator = Creator.builder()
                .id(1L)
                .birth("921102")
                .sex(0)
                .build();
        Contract contract = Contract.builder()
                .channel(channel)
                .creator(creator)
                .contractStatusType(ContractStatusType.COMPLETE)
                .companyRate(30)
                .creatorRate(70)
                .build();

        Mockito.when(channelService.save(ArgumentMatchers.any()))
                .thenReturn(channel);
        Mockito.when(creatorService.saveAll(ArgumentMatchers.any()))
                .thenReturn(Collections.singletonList(creator));

        ContractService mock = mock(ContractService.class);
        mock.saveAll(Collections.singletonList(contract));

        verify(mock, times(1)).saveAll(Collections.singletonList(contract));

        contractFacade.contractAndChannelSave(request);
    }
}