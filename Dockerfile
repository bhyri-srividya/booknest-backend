# Use Java 17 base image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy jar file (make sure jar is built)
COPY target/*.jar app.jar

# Run the application
ENTRYPOINT ["java","-jar","/app/app.jar"]
