
FROM maven:3.8.1-openjdk-11 AS builder

WORKDIR /app

COPY pom.xml .
COPY ./mvnw .
COPY ./pom.xml .

COPY src/ ./src/

RUN mvn clean package -DskipTests

FROM openjdk:11-jdk-slim

WORKDIR /app

COPY target/Challenge_Tenpo-0.0.1-SNAPSHOT.jar /app/Challenge_Tenpo-0.0.1-SNAPSHOT.jar

ENV PORT 8001

EXPOSE $PORT

ENTRYPOINT ["java", "-jar", "Challenge_Tenpo-0.0.1-SNAPSHOT.jar"]

