# syntax=docker/dockerfile:1

FROM eclipse-temurin:21-alpine

WORKDIR /app

COPY gradle gradle
COPY gradlew build.gradle.kts ./
RUN ./gradlew build

COPY src ./src

ENTRYPOINT ["bash"] 