# Use a base image with Java installed
FROM openjdk:17-jdk-slim as build

# Set the working directory inside the container
WORKDIR /app

# Copy the build artifacts from your local system to the container
COPY target/*.jar app.jar

# Expose the application port (e.g., 8080)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]