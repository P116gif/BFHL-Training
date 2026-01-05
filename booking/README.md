# Booking Application

A **Spring Boot–based Booking Application** designed using **Clean Architecture / Hexagonal Architecture** principles. The project models an event booking system with authentication, seat selection, pricing, notifications, and integrations with Redis and RabbitMQ.

This repository reflects an educational, layered architecture approach where **business logic is isolated from frameworks and infrastructure concerns**.

---

## 🧱 Architecture Overview

The project is organized into four main layers:

```
com.example.booking
├── application     # Use cases / application services
├── domain          # Core business logic
├── infrastructure  # Frameworks, persistence, messaging, security
├── interfaces      # REST controllers, DTOs, external APIs
└── BookingApplication.java
```

### 1. Application Layer (`application`)

Contains **use cases** that orchestrate domain logic. These classes represent what the system *does*.

**Examples:**

* `BrowsingUseCase`
* `SeatBookingUseCase`
* `PricingUseCase`
* `ShowSeatUseCase`
* `AuditoriumUseCase`

This layer:

* Depends on **domain abstractions (repositories, models)**
* Does **not** depend on Spring, persistence, or messaging frameworks

---

### 2. Domain Layer (`domain`)

The **heart of the application**. Contains pure business logic.

#### Models (`domain.models`)

* `User`
* `Role`
* `SeatCategory`
* `SeatStatus`
* `BookingConfirmedEvent`

#### Repositories (`domain.repository`)

Repository interfaces that define *what* data access is needed, not *how*.

Examples:

* `UserRepository`
* `EventRepository`
* `ShowRepository`
* `VenueRepository`

➡️ Implementations live in the infrastructure layer.

---

### 3. Infrastructure Layer (`infrastructure`)

Provides **technical implementations** for persistence, messaging, security, and configuration.

#### Persistence

* JPA repositories (`JpaUserRepository`, etc.)
* Entities under `persistence/Entities`
* Redis token storage (`RedisTokenStore`)

#### Messaging

* RabbitMQ configuration (`RabbitMQConfig`)
* Event publisher (`BookingEventPublisher`)

#### Security

* JWT-based authentication
* `JwtAuthenticationFilter`
* `JwtServiceImplementation`
* `PasswordHashingBCrypt`

#### Configurations

* `SecurityConfig`
* `RedisConfig`
* `RabbitMQConfig`

This layer **depends on Spring Boot and external frameworks**.

---

### 4. Interfaces Layer (`interfaces`)

Handles communication with the outside world (HTTP, REST, DTO mapping).

#### REST Controllers (`interfaces.Rest.Controllers`)

* `LoginController`
* `RegisterController`
* `EventAdminController`
* `SeatBookingController`
* `VenueController`
* `AuditoriumController`
* `ProfileController`

#### DTOs

* Request/Response objects
* Prevents leaking domain models directly to the API

#### Mappers

* `UserMapper`

---

## 🔐 Authentication & Security

* JWT-based authentication
* Access & refresh tokens
* Refresh tokens stored in Redis
* BCrypt password hashing
* Custom JWT authentication filter

---

## 📦 Messaging & Events

* Uses **RabbitMQ** for asynchronous communication
* Publishes domain events such as `BookingConfirmedEvent`
* Designed to support notifications or downstream services

---

## 🗄️ Data Storage

* **Relational database** via Spring Data JPA
* **Redis** for refresh token storage
* Repository interfaces defined in the domain layer

---

## 🗂️ System Architecture Diagram

```
┌──────────────┐      HTTP / JSON       ┌────────────────────────┐
│   Client     │ ────────────────────▶ │   REST Controllers      │
│ (Web / API)  │                       │ (interfaces layer)      │
└──────────────┘                       └────────────┬───────────┘
                                                     │
                                                     ▼
                                        ┌────────────────────────┐
                                        │   Application Layer    │
                                        │   (Use Cases)          │
                                        └────────────┬───────────┘
                                                     │
                                                     ▼
                                        ┌────────────────────────┐
                                        │     Domain Layer       │
                                        │  (Models + Rules)     │
                                        └────────────┬───────────┘
                                                     │
                  ┌──────────────────────┬──────────┴──────────┬──────────────────────┐
                  ▼                      ▼                     ▼                      ▼
        ┌────────────────┐   ┌────────────────┐   ┌────────────────┐   ┌────────────────┐
        │   JPA / DB      │   │     Redis      │   │   RabbitMQ     │   │  Security/JWT  │
        │ (Persistence)  │   │ (Tokens)       │   │ (Events)       │   │ (Auth Filter)  │
        └────────────────┘   └────────────────┘   └────────────────┘   └────────────────┘
```

**Key Flow Example:**

1. Client sends request to REST controller
2. Controller delegates to a use case
3. Use case executes domain logic
4. Persistence/messaging handled via infrastructure implementations

---

## 🌐 API Endpoint Examples

> Base URL: `/api`

### 🔐 Authentication

**Register**

```http
POST /api/register
```

```json
{
  "email": "user@example.com",
  "password": "password123",
  "name": "John Doe",
  "phone":"9999999999"
}
```

**Login**

```http
POST /api/login
```

```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

**Refresh Token**

```http
POST /api/auth/refresh
```

---

### 👤 User Profile

**Get Profile**

```http
GET /api/profile
Authorization: Bearer <access_token>
```

---

### 🎭 Events & Venues

**List Venues**

```http
GET /api/venues/listAllvenues
```

**Create Event (Admin)**

```http
POST /api/admin/event/create
Authorization: Bearer <admin_token>
```

```json
{
  "title": "Rock Concert",
  "language": "hindi",
  "genre":"action",
  "duration":3,
  "rating": "A"
}
```

---

### 💺 Seats & Booking

**Book a Seat**

```http
POST /api/book/{showId}/{seatId}
Authorization: Bearer <access_token>
```

📨 Successful booking publishes a `BookingConfirmedEvent` to RabbitMQ.

---

## 🚀 Running the Application

### Prerequisites

* Java 17+
* Maven
* Docker (recommended for Redis & RabbitMQ)

### Build

```bash
mvn clean install
```

### Run

```bash
mvn spring-boot:run
```

Or run `BookingApplication.java` from your IDE.

---

## 🧪 Testing (Suggested)

While not fully implemented yet, the architecture will support:

* Unit tests for **domain models**
* Unit tests for **use cases** (mocking repositories)
* Integration tests for controllers & persistence

---

## 🎯 Design Principles Used

* Clean Architecture
* SOLID principles
* Dependency Inversion
* Separation of Concerns
* Domain-Driven Design (DDD–inspired)

---

## 📌 Notes

* The project is intentionally verbose to demonstrate architectural boundaries.
* Package-by-feature is used at higher levels (Login, Events, Profile, Register).
* Infrastructure code can be swapped without affecting core business logic.

---

## 👤 Author

Developed as part of an architectural and backend training project.

---

## 📄 License

This project is for educational purposes.
