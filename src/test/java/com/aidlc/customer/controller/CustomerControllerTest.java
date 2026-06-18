package com.aidlc.customer.controller;

import com.aidlc.customer.dto.CustomerResponse;
import com.aidlc.customer.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService service;

    @Test
    void shouldReturnAllCustomers() throws Exception {

        CustomerResponse response =
                CustomerResponse.builder()
                        .customerId("CUST-123")
                        .fullName("Bhaskar")
                        .email("bhaskar@test.com")
                        .build();

        when(service.getAllCustomers())
                .thenReturn(List.of(response));

        mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].fullName")
                        .value("Bhaskar"));
    }
}