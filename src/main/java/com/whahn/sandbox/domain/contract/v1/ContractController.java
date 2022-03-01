package com.whahn.sandbox.domain.contract.v1;

import com.whahn.sandbox.common.ApiResponse;
import com.whahn.sandbox.common.Constant;
import com.whahn.sandbox.domain.contract.v1.request.ContractRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;

@Tag(name = "계약 API", description = "계약 API LIST")
@RestController
@RequestMapping(value = "/api/v1/contract", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContractController {
    @Operation(summary = "계약정보 및 유튜브 채널 등록 API")
    @PostMapping
    public ApiResponse<Void> saveContractAndChannel(/*@RequestBody ContractRequest.Save request*/) {

        return ApiResponse.success();
    }
}
