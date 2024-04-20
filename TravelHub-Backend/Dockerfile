# Use a base image with Java and Maven installed
FROM openjdk:17-alpine as builder

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project file
COPY pom.xml .

# Download dependencies
RUN ["./mvnw", "dependency:go-offline"]

# Copy the project source
COPY src ./src

# Build the application
RUN ["./mvnw", "package", "-DskipTests"]

# Create a minimal runtime image
FROM openjdk:17-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
