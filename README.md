# Prescription Backend (Spring Boot)

Features
- Spring Boot 3, H2 (file), JPA, Validation, Spring Security (HTTP Basic), OpenAPI/Swagger UI
- CRUD endpoints for prescriptions
- Day-wise prescription count report
- Date range query with default to current month
- Users stored in DB with BCrypt (seeded)

## Run
```bash
mvn spring-boot:run
```
- Swagger UI: http://localhost:8080/swagger-ui.html
- API Docs: http://localhost:8080/api-docs

### Default Users
- admin / password
- doctor / password

## CORS
Permits http://localhost:4200 (Angular app). Edit `app.cors.allowed-origin` in `application.properties` if needed.
