# Stage 1: Build the Spring Boot application
FROM openjdk:17-jdk-alpine as builder

# Install Maven
RUN apk add --no-cache maven

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project file
COPY pom.xml .

# Copy the project source
COPY src ./src

# Run mvn clean install to build the application
RUN mvn clean install -DskipTests

# Stage 3: Create the final image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the compiled JAR file from the build environment to the container
COPY --from=builder /app/target/*.jar app.jar

# Specify the entry point command to run the Spring Boot application when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]
