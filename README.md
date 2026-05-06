# Point Of Interest Service

REST API application built with Spring Boot and PostgreSQL/PostGIS.

## Features

- Create Point Of Interest (POI)
- Find nearest POI by coordinates
- In-memory POI caching
- Request counter tracking
- Retrieve popular POIs
- Validation and exception handling
- Geospatial support using PostGIS

## Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- PostGIS
- Hibernate Spatial
- Maven
- Docker

## Run PostgreSQL/PostGIS

```bash
docker compose -f docker/docker-compose.yml up -d
```

## Run Application

```bash
./mvnw spring-boot:run
```

Application runs on:

```text
http://localhost:8080
```

## API Endpoints

### Create POI

POST `/api/pois`

Example request:

```json
{
  "name": "Athens Center",
  "latitude": 37.9838,
  "longitude": 23.7275
}
```

---

### Find Nearest POI

POST `/api/pois/nearest`

```json
{
  "latitude": 37.98,
  "longitude": 23.72
}
```

---

### Get Popular POIs

GET `/api/pois/popular?minCounter=1`

---

### Get All POIs

GET `/api/pois`

## Design Notes

- POIs are cached in memory to avoid database scanning on every nearest-point request.
- Coordinates are stored using PostGIS geometry type.
- JPA/Hibernate is used for persistence operations.
- Validation is implemented using Jakarta Validation.

## Run Tests

```bash
./mvnw test
```

## Swagger UI

```text
[http://localhost:8080/swagger-ui/index.html](https://improved-computing-machine-xr5gj9r6759cv9rr-8080.app.github.dev/swagger-ui/index.html#/)
```
