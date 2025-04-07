# ------------ Stage 1: Build JAR ------------

FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests


# ------------ Stage 2: Run App ------------

# Use OpenJDK 17 as the base image
FROM openjdk:17-jdk-slim

# Set working directory inside the container
WORKDIR /app

# Copy the fat JAR into the image
COPY --from=build /app/target/*.jar app.jar


# Expose the desired port (Render will respect this if you're binding to it)
EXPOSE 9080

# Start the Spring Boot application with explicit logging config
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-Dserver.port=9080", "-jar", "app.jar"]
