package com.aidlc.customer.service;

import com.aidlc.customer.dto.CustomerRequest;
import com.aidlc.customer.dto.CustomerResponse;
import com.aidlc.customer.entity.Customer;
import com.aidlc.customer.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository repository;

    @InjectMocks
    private CustomerServiceImpl service;

    private CustomerRequest request;

    @BeforeEach
    void setup() {
        request = new CustomerRequest();
        request.setFullName("Bhaskar");
        request.setEmail("bhaskar@test.com");
        request.setMobileNumber("+919999999999");
        request.setKycStatus("PENDING");
    }

    @Test
    void shouldCreateCustomerSuccessfully() {

        when(repository.existsByEmail(anyString())).thenReturn(false);

        Customer customer = Customer.builder()
                .id(1L)
                .customerId("CUST-1234")
                .fullName("Bhaskar")
                .email("bhaskar@test.com")
                .mobileNumber("+919999999999")
                .kycStatus("PENDING")
                .build();

        when(repository.save(any(Customer.class)))
                .thenReturn(customer);

        CustomerResponse response =
                service.createCustomer(request);

        assertNotNull(response);
        assertEquals("Bhaskar", response.getFullName());

        verify(repository, times(1)).save(any());
    }

    @Test
    void shouldThrowExceptionWhenEmailExists() {

        when(repository.existsByEmail(anyString()))
                .thenReturn(true);

        RuntimeException exception =
                assertThrows(RuntimeException.class,
                        () -> service.createCustomer(request));

        assertEquals(
                "Customer email already exists",
                exception.getMessage());

        verify(repository, never()).save(any());
    }
}