package com.whahn.sandbox.domain.contract.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whahn.sandbox.domain.channel.v1.ChannelController;
import com.whahn.sandbox.domain.contract.ContractFacade;
import com.whahn.sandbox.domain.contract.v1.request.BasicContractRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(
        value = { ContractController.class }
)
class ContractControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ContractFacade contractFacade;

    @Test
    @DisplayName("[성공] 계약정보 및 유튜브 채널 등록 API 테스트")
    void saveContractAndChannelSuccessTest() throws Exception {
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

        String body = new ObjectMapper().writeValueAsString(request);

        mockMvc.perform(
                        post("/api/v1/contract")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(body)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("success"))
        // .andExpect(content().string())
        ;
    }
}