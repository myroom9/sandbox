package com.whahn.sandbox.domain.settlement;

import com.whahn.sandbox.domain.channel.Channel;
import com.whahn.sandbox.domain.channel.ChannelService;
import com.whahn.sandbox.domain.settlement.v1.request.BasicSettlementRequest;
import com.whahn.sandbox.exception.cumtom.SettlementRegisterException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SettlementService {
    private final SettlementRepository settlementRepository;
    private final ChannelService channelService;

    public List<Settlement> saveDailySaveAll(List<BasicSettlementRequest.SaveRequest> request) {
        List<Settlement> settlements = request.stream()
                .map(settlementRequest -> {
                    Channel channel = channelService.findById(settlementRequest.getChannelId());
                    return settlementRequest.toEntity(channel);
                })
                .collect(Collectors.toList());

        return Optional.of(settlementRepository.saveAll(settlements))
                .orElseThrow(SettlementRegisterException::new);
    }
}
