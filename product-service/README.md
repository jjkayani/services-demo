# Read Me First
This is a product service which is built using spring-boot 2xx and REST API to consume the adidas product api and the api from review-service.

# Getting Started
This service is running on port 8090, you can access the below URL to access swagger and call the api:
* http://localhost:8090/swagger-ui/index.html#/

# Security

The api is protected by spring-security and needs an addtional header which containt the secret key to access the apis.

* x-application-secret: 92d4f86r-8768a-4b79m-8a7a-c97d33722019

While using swagger the app secret can be passed in authorize section.

# Dockerized Solution

The project also contain a docker file if you want to run the application as container. Please use the below commands.

* docker build  -t product-service:0.0.1-SNAPSHOT .
* docker run --name product-service -p 8090:8090 product-service:0.0.1-SNAPSHOT


# Integraion Test
* Integration test is placed under/test/integration-test package.
* Integration tests can be run with this command [ newman run integration.json ] or they can be imported in Postman.