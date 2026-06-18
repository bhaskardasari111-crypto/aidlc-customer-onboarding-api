package com.aidlc.customer.service;

import com.aidlc.customer.dto.CustomerRequest;
import com.aidlc.customer.dto.CustomerResponse;
import com.aidlc.customer.entity.Customer;
import com.aidlc.customer.exception.CustomerNotFoundException;
import com.aidlc.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    @Override
    public CustomerResponse createCustomer(CustomerRequest request) {

        log.info("Creating customer with email {}", request.getEmail());

        if (repository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Customer email already exists");
        }

        Customer customer = Customer.builder()
                .customerId("CUST-" + UUID.randomUUID().toString().substring(0, 8))
                .fullName(request.getFullName())
                .email(request.getEmail())
                .mobileNumber(request.getMobileNumber())
                .kycStatus(request.getKycStatus())
                .createdDate(LocalDateTime.now())
                .build();

        Customer savedCustomer = repository.save(customer);

        log.info("Customer created successfully {}", savedCustomer.getCustomerId());

        return mapToResponse(savedCustomer);
    }

    @Override
    public CustomerResponse getCustomer(Long id) {

        Customer customer = repository.findById(id)
                .orElseThrow(() ->
                        new CustomerNotFoundException(
                                "Customer not found with id " + id));

        return mapToResponse(customer);
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {

        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public CustomerResponse updateCustomer(Long id,
                                           CustomerRequest request) {

        Customer customer = repository.findById(id)
                .orElseThrow(() ->
                        new CustomerNotFoundException(
                                "Customer not found with id " + id));

        customer.setFullName(request.getFullName());
        customer.setEmail(request.getEmail());
        customer.setMobileNumber(request.getMobileNumber());
        customer.setKycStatus(request.getKycStatus());

        Customer updatedCustomer = repository.save(customer);

        return mapToResponse(updatedCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {

        Customer customer = repository.findById(id)
                .orElseThrow(() ->
                        new CustomerNotFoundException(
                                "Customer not found with id " + id));

        repository.delete(customer);

        log.info("Customer deleted successfully {}", id);
    }

    private CustomerResponse mapToResponse(Customer customer) {

        return CustomerResponse.builder()
                .id(customer.getId())
                .customerId(customer.getCustomerId())
                .fullName(customer.getFullName())
                .email(customer.getEmail())
                .mobileNumber(customer.getMobileNumber())
                .kycStatus(customer.getKycStatus())
                .createdDate(customer.getCreatedDate())
                .build();
    }
}