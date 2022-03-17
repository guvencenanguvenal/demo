
# reading-is-good

## Getting Started

    - Please take a token before call services (http://localhost:8080/api/auth/token)
    - There is one user for token (username = admin, password = admin)
    - There are 5 controller (Auth, Book, Order, Customer, Statistics)
    - You can check controller with Swagger (http://localhost:8080/swagger-ui/index.html)

## Tech Stack

    - docker compose (please check docker-compose.yml)
    - MongoDB 4.4 (because of my internet problem, it's not specific version.)
    - REST Api developed with Spring Boot.
    - Open API Swagger 3 (with default config)
    - Auth service JWT with Spring Security and jjwt.
    - mapping library ModelMapper (encupsulated with ModelUtil service)
    - MongoRepository

## Assumption

    * has one environment (there is one property file)
    * JWT token users implement hard coded
    * validation ISBN works fine. (all of @ISBN validator comment out)

# Usage

## Running

    docker-compose up

## Security

    - JWT Token username and password are admin-admin 

    - for Token *http://localhost:8080/api/auth/token*

Expected Request

```json
{
  "username": "admin",
  "password": "admin"
}
```

## Swagger

    Swagger default properties is avaiable [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
