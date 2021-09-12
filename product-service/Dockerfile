FROM openjdk:8-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} product-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/product-service-0.0.1-SNAPSHOT.jar"]