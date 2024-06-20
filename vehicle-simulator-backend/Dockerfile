# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17-slim AS builder
WORKDIR /app

COPY ./vehicle-simulator-backend .
RUN mvn clean install



# Stage 2: Create the final lightweight image for runtime
FROM maven:3.8.4-openjdk-17-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file built in the previous stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the port your application listens on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]
