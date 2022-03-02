package com.whahn.sandbox.domain.creator;

import com.whahn.sandbox.domain.channel.Channel;
import com.whahn.sandbox.domain.contract.v1.request.BasicContractRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreatorService {
    private final CreatorRepository creatorRepository;

    public List<Creator> saveAll(List<BasicContractRequest.CreatorRequest> creatorsRequest, Channel channel) {
        List<Creator> creators = creatorsRequest.stream()
                .map(creator -> creator.toEntity(creator, channel))
                .collect(Collectors.toList());
        return creatorRepository.saveAll(creators);
    }
}
