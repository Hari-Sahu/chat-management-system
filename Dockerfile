# Use OpenJDK 17 as the base image
FROM openjdk:17-jdk-slim

# Set working directory inside the container
WORKDIR /app

# Copy the fat JAR into the image
COPY target/*.jar app.jar


# Expose the desired port (Render will respect this if you're binding to it)
EXPOSE 9080

# Start the Spring Boot application with explicit logging config
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-Dserver.port=9080", "-jar", "app.jar"]
