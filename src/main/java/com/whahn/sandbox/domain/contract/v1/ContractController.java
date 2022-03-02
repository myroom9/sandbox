package com.whahn.sandbox.domain.contract.v1;

import com.whahn.sandbox.common.ApiResponse;
import com.whahn.sandbox.domain.contract.ContractFacade;
import com.whahn.sandbox.domain.contract.v1.request.BasicContractRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Tag(name = "계약 API", description = "계약 API LIST")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/contract", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContractController {

    private final ContractFacade contractFacade;

    @Operation(summary = "계약정보 및 유튜브 채널 등록 API")
    @PostMapping
    public ApiResponse<Void> saveContractAndChannel(@Valid @RequestBody BasicContractRequest.Save request) {
        // 시간되면 validator도 생성해서 추가해도될듯
        log.info("계약정보 및 유튜브 채널 등록 API 요청 데이터: {}", request);
        contractFacade.contractAndChannelSave(request);

        return ApiResponse.success();
    }
}
