package com.whahn.sandbox.domain.contract;

import com.whahn.sandbox.common.ErrorCode;
import com.whahn.sandbox.domain.channel.Channel;
import com.whahn.sandbox.domain.contract.v1.request.BasicContractRequest;
import com.whahn.sandbox.domain.creator.Creator;
import com.whahn.sandbox.exception.cumtom.BusinessException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContractServiceTest {
    @Mock
    private ContractRepository contractRepository;

    @InjectMocks
    private ContractService contractService;

    @Test
    @DisplayName("[성공] 계약 정보 등록 테스트")
    void saveAllSuccessTest() {
        BasicContractRequest.ContractRequest contractRequest = BasicContractRequest.ContractRequest.builder()
                .companyRate(30)
                .creatorRate(70)
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

        ContractService mock = mock(ContractService.class);
        mock.saveAll(Collections.singletonList(contract));

        verify(mock, times(1)).saveAll(Collections.singletonList(contract));
    }

    @Test
    @DisplayName("[성공] 계약 조회 by channel 테스트")
    void findAllByChannelSuccessTest() {
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

        Mockito.when(contractService.findAllByChannel(channel))
                .thenReturn(Collections.singletonList(contract));

        List<Contract> result = contractService.findAllByChannel(channel);

        Contract contract1 = result.get(0);
        Assertions.assertThat(result.size()).isEqualTo(1);
        Assertions.assertThat(contract1.getChannel()).isEqualTo(channel);
        Assertions.assertThat(contract1.getCompanyRate()).isEqualTo(30);
        Assertions.assertThat(contract1.getCreatorRate()).isEqualTo(70);
        Assertions.assertThat(contract1.getContractStatusType()).isEqualTo(ContractStatusType.COMPLETE);
        Assertions.assertThat(contract1.getCreator()).isEqualTo(creator);
    }

    @Test
    @DisplayName("[예외] 계약 조회 by channel 예외 테스트")
    void findAllByChannelExceptionTest() {
        Mockito.when(contractRepository.findAllByChannel(ArgumentMatchers.any()))
                .thenThrow(new BusinessException(ErrorCode.CONTRACT_NOT_EXCEPTION));

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            contractService.findAllByChannel(new Channel("test channel"));
        });

        Assertions.assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.CONTRACT_NOT_EXCEPTION);
    }
}