package com.whahn.sandbox.domain.channel.v1;

import com.whahn.sandbox.common.ApiResponse;
import com.whahn.sandbox.domain.channel.ChannelFacade;
import com.whahn.sandbox.domain.channel.v1.request.ChannelRequest;
import com.whahn.sandbox.domain.channel.v1.response.ChannelResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static com.whahn.sandbox.domain.channel.v1.response.ChannelResponse.*;

@Slf4j
@Tag(name = "유튜브 채널관련 API", description = "유튜브 채널관련 API LIST")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/channel", produces = MediaType.APPLICATION_JSON_VALUE)
public class ChannelController {

    private final ChannelFacade channelFacade;

    @Operation(summary = "[월별 ]유튜브 채널 수익금 조회 및 크리에이터별 정산금액 조회")
    @GetMapping("/{channelId}/monthly/sales")
    public ApiResponse<List<ChannelSalesAndCreatorSettlementAmount>> searchSalesAmountAndCreatorSettleAmount(@Valid ChannelRequest.SearchSalesAmount request,
                                                                                                             @PathVariable("channelId") Long channelId) {
        List<ChannelSalesAndCreatorSettlementAmount> response = channelFacade.searchSalesAndCreatorSettlement(channelId, request);
        return ApiResponse.success(response);
    }

}
