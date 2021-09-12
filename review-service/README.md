# Read Me First
This is a review service which is built using spring-boot 2xx and REST API and h2 in memory data. Few of the records
will be populated on the application startup. Please check src/main/resources/data.sql for seeded records.

# Getting Started
This service is running on port 8085, you can access the below URL to access swagger and call the api:
* http://localhost:8085/swagger-ui/index.html#/

# Security

The api is protected by spring-security and needs an additional header which requires the secret key to access the apis.

* x-application-secret: 82d4f86f-8768-4b79-8a7e-c97d33725684

While using swagger the app secret can be passed in authorize section.

# Dockerized Solution

The project also contain a docker file if you want to run the application as container. Please use the below commands.

* docker build  -t review-service:0.0.1-SNAPSHOT .
* docker run --name review-service -p 8085:8085 review-service:0.0.1-SNAPSHOT


# Integraion Test
* Integration test is placed under/test/integration-test package.
* Integration tests can be run with this command [ newman run integration.json ] or they can be imported in Postman.