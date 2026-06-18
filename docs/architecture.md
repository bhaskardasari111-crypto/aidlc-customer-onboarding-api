# Customer Onboarding API - Architecture

## High Level Architecture

```text
+------------------+
|      Client      |
| Swagger / REST   |
+--------+---------+
         |
         v
+------------------+
| CustomerController|
| REST Endpoints   |
+--------+---------+
         |
         v
+------------------+
| CustomerService  |
| Business Logic   |
+--------+---------+
         |
         v
+------------------+
|CustomerRepository|
| Spring Data JPA  |
+--------+---------+
         |
         v
+------------------+
|   H2 Database    |
+------------------+
```

## Request Flow

```text
Client Request
      |
      v
CustomerController
      |
      v
CustomerRequest DTO
      |
      v
CustomerService
      |
      v
Business Validation
      |
      v
Customer Entity
      |
      v
CustomerRepository
      |
      v
H2 Database
      |
      v
CustomerResponse DTO
      |
      v
Client Response
```

## Component Structure

```text
src/main/java/com/aidlc/customer

├── controller
│   └── CustomerController
│
├── service
│   ├── CustomerService
│   └── CustomerServiceImpl
│
├── repository
│   └── CustomerRepository
│
├── entity
│   └── Customer
│
├── dto
│   ├── CustomerRequest
│   └── CustomerResponse
│
├── exception
│   └── GlobalExceptionHandler
│
└── config
    └── OpenApiConfig
```

## Testing Architecture

```text
src/test/java

├── controller
│   └── CustomerControllerTest
│
├── service
│   └── CustomerServiceTest
│
└── integration
    └── CustomerIntegrationTest
```

## AIDLC Workflow

```text
Requirement
    |
    v
Prompt Engineering
    |
    v
AI Assisted Code Generation
    |
    v
Developer Review
    |
    v
Unit Testing
    |
    v
Integration Testing
    |
    v
Code Review Checklist
    |
    v
Release Readiness Review
    |
    v
Production Ready Build
```

## Technology Stack

- Java 21
- Spring Boot 3.5
- Spring Data JPA
- H2 Database
- Swagger / OpenAPI
- JUnit 5
- Mockito
- Maven
