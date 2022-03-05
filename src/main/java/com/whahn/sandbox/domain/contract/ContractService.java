package com.whahn.sandbox.domain.contract;

import com.whahn.sandbox.common.ErrorCode;
import com.whahn.sandbox.common.ModelMapperUtil;
import com.whahn.sandbox.domain.channel.Channel;
import com.whahn.sandbox.domain.contract.v1.request.BasicContractRequest;
import com.whahn.sandbox.domain.creator.Creator;
import com.whahn.sandbox.exception.cumtom.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.whahn.sandbox.domain.contract.v1.request.BasicContractRequest.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContractService {
    private final ContractRepository contractRepository;

    public List<Contract> saveAll(ContractRequest contractRequest, Channel channel, List<Creator> creators) {
        List<Contract> contracts = creators.stream().map(creator -> {
            Contract contract = new Contract(contractRequest.getCreatorRate(), contractRequest.getCompanyRate());
            contract.addStatus();
            contract.addChannelAndCreator(channel, creator);
            return contract;
        }).collect(Collectors.toList());
        contractRepository.saveAll(contracts);

        return contracts;
    }

    public List<Contract> findAllByChannel(Channel channel) {
        return Optional.of(contractRepository.findAllByChannel(channel))
                .orElseThrow(() -> new BusinessException(ErrorCode.CONTRACT_NOT_EXCEPTION));
    }
}
