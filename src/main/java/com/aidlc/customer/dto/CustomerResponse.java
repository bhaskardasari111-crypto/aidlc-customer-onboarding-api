package com.aidlc.customer.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CustomerResponse {

    private Long id;
    private String customerId;
    private String fullName;
    private String email;
    private String mobileNumber;
    private String kycStatus;
    private LocalDateTime createdDate;
}