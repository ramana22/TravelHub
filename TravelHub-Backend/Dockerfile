# Use a base image with Java and Maven installed
FROM maven:3.8.3-openjdk-17 as builder

# Set the working directory in the container
WORKDIR /app

# Copy the Maven Wrapper script and project files
COPY .mvn .mvn
COPY mvnw pom.xml ./
COPY src ./src

# Download dependencies and build the application
RUN ./mvnw dependency:go-offline package -DskipTests

# Create a minimal runtime image
FROM adoptopenjdk/openjdk17:alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
