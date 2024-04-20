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

# Copy the Maven Wrapper script and project files
COPY .mvn .mvn
COPY mvnw pom.xml ./
COPY src ./src

# Download dependencies and build the application
RUN ./mvnw dependency:go-offline package -DskipTests

# Create a minimal runtime image
FROM adoptopenjdk/openjdk17:jdk-17.0.2_8-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
