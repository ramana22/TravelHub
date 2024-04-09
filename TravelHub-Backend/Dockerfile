# Use the official OpenJDK 17 image as the base image
FROM openjdk:17-jdk-alpine

# Add the compiled JAR file from the target directory to the Docker image
ADD target/travelhub.jar app.jar

# Specify the entry point command to run the Spring Boot application when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]
