# Stage 1: Build the app
FROM maven:3.9.4-eclipse-temurin-17 AS build
COPY . /app
WORKDIR /app
RUN mvn clean package -DskipTests

# Stage 2: Run the app
FROM openjdk:17-jdk-slim
COPY --from=build /app/target/backend-1.0.0.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
