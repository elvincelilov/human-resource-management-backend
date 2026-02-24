# Human Resource Management System — Backend (Spring Boot)

A production-style Human Resource Management backend built with **Spring Boot 3**, **Spring Security (JWT)**, **MySQL**, **JPA/Hibernate**, **Swagger (OpenAPI)**, and **Docker**.

This project demonstrates secure role-based backend architecture with real business features such as leave management, project assignment, salary management, and dashboard analytics.

---

##  What This Backend Provides

- JWT-based authentication (stateless security)
- Role-based authorization (ADMIN / EMPLOYEE)
- User approval workflow
- Leave request management (approve / reject)
- Project creation and employee assignment
- Salary assignment
- Admin dashboard statistics
- Pagination & sorting
- Global exception handling
- Swagger UI for interactive API testing
- Dockerized setup

---

##  Architecture Overview

Client (Swagger / Postman / Frontend)
↓
Spring Boot REST API
├─ Security (JWT Filter)
├─ Controllers (Admin / Employee / Auth)
├─ Services (Business Logic)
├─ Repositories (JPA)
└─ MySQL Database

### Key Design Decisions

- Stateless authentication using JWT
- Layered architecture (Controller → Service → Repository)
- DTO-based responses
- Clean separation of business logic
- Role-based endpoint restrictions
- Consistent JSON error responses

---

##  Tech Stack

- Java 17
- Spring Boot 3.3.x
- Spring Security (JWT)
- Spring Data JPA (Hibernate)
- MySQL
- Swagger (springdoc-openapi)
- Docker & Docker Compose
- Lombok

---

##  Security & Authorization

- Stateless JWT authentication
- Access token required for protected endpoints
- Role-based restrictions:
    - `/api/admin/**` → ADMIN only
    - `/api/employee/**` → EMPLOYEE only

Public endpoints:
- `/api/auth/**`
- `/swagger-ui/**`
- `/v3/api-docs/**`

Authorization header example:
--Authorization: Bearer <access_token>

##  Main Business Features

###  User Management
- User registration
- Admin approval / rejection
- Role-based access control

###  Leave Management
- Submit leave request (EMPLOYEE)
- Approve / reject leave (ADMIN)
- Paginated leave listing

###  Project Management
- Create project (ADMIN)
- Assign employee to project
- Paginated project listing

###  Salary Management
- Assign salary to employee (ADMIN)

###  Dashboard
- Aggregated admin statistics

---

## API Documentation

Swagger UI available at:
http://localhost:9009/swagger-ui/index.html

All endpoints can be tested interactively via Swagger.

---

## ⚙ Running Locally
Run:
  - mvn clean install
  - mvn spring-boot:run

#  Docker Configuration

## Dockerfile (Multi-Stage)

Stage 1 — Build:

- Maven 3.9 + Temurin 17
- Dependency caching
- Package fat JAR

Stage 2 — Runtime:

- eclipse-temurin:17-jre-jammy
- Exposes port 9009
- Runs: `java -jar app.jar`

---

## docker-compose.yml

Services:

### MySQL
- Image: mysql:8.0
- Database: hrm_db
- Port: 3309 → 3306
- Persistent volume: mysql_data

### Spring Boot App
- Port: 9009
- Environment-based DB configuration
- Depends on MySQL

Internal connection:  jdbc:mysql://mysql:3306/hrm_db

---

#  Environment Configuration

Uses environment variables:
- SPRING_DATASOURCE_URL : mysql://localhost:3306/hrm_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
- SPRING_DATASOURCE_USERNAME : root
- SPRING_DATASOURCE_PASSWORD : admin
- SERVER_PORT : 9009

application.properties:

- spring.datasource.url=${SPRING_DATASOURCE_URL}
- spring.datasource.username=${SPRING_DATASOURCE_USERNAME}
- spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}
- server.port=${SERVER_PORT}

---

##  Database

Entities:

- User
- Employee
- Project
- LeaveRequest
- Salary
- ProjectReport

Managed using Spring Data JPA & Hibernate.

---

##  Testing

API can be tested using:

- Swagger UI
- Postman

JWT token must be included in the Authorization header for protected endpoints.

---

##  Future Improvements

- Email notifications
- File upload support
- Unit & integration tests
- Audit logging
- Extended role management

---

##  License

Apache-2.0