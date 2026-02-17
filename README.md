Interview & Application Tracker Backend
-A Spring Boot REST API to track job applications, interview rounds, status transitions, and reminders.
-Built to model real-world backend architecture using layered design and clean API contracts.

Tech Stack
-Java 17
-Spring Boot
-Spring Data JPA
-H2 (development)
-Maven

Features
-Create / Update / Delete job applications
-Track application status (APPLIED → OA → INTERVIEW → OFFER / REJECTED)
-Add multiple interview rounds per application
-Business rule validation for status transitions
-Pagination + filtering support
-DTO-based clean API responses
-Global exception handling
-Layered architecture (Controller → Service → Repository)

Architecture
-controller → handles HTTP layer
-service → business logic
-repository → database interaction
-domain → entities + enums
-dto → API request/response models
-exception → global error handling

API Endpoints
Applications

Create Application
```
POST /applications
```

Get Applications (Paginated)
```
GET /applications?page=0&size=10
GET /applications?status=APPLIED&page=0&size=5
```

Get By ID
```
GET /applications/{id}
```

Update
```
PUT /applications/{id}
```

Delete
```
DELETE /applications/{id}
```

Update Status
```
PATCH /applications/{id}/status?status=INTERVIEW
```
Interview Rounds

Add Round
```
POST /applications/{id}/rounds?type=TECHNICAL
```

Get Rounds
```
GET /applications/{id}/rounds
```
Sample Paginated Response
```
{
  "content": [
    {
      "id": 1,
      "companyName": "Amazon",
      "role": "SDE",
      "status": "APPLIED",
      "notes": "Applied via referral",
      "createdAt": "...",
      "updatedAt": "..."
    }
  ],
  "page": 0,
  "size": 20,
  "totalElements": 1,
  "totalPages": 1
}
```

Design Decisions
-Enums used instead of strings for status safety
-DTOs used to prevent entity exposure
-Pagination wrapped in custom response to avoid leaking Spring internals
-Business rules enforced in service layer
-Relationship mapping via JPA (@OneToMany, @ManyToOne)

Running Locally
Clone repository
Run:
```
mvn spring-boot:run
```
Access:
```
http://localhost:8080
```
H2 Console:
```
http://localhost:8080/h2-console
```
JDBC URL:
```
jdbc:h2:mem:interviewdb
```
