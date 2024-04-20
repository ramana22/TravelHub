# Use a base image with Java and Maven installed
FROM adoptopenjdk/openjdk17:alpine as builder

# Set the working directory in the container
WORKDIR /app

# Copy the Maven Wrapper script and project files
COPY .mvn .mvn
COPY mvnw ./
COPY src ./src
COPY pom.xml .

# Ensure Maven Wrapper script is executable
RUN chmod +x mvnw

# Run mvn clean install to build the application
RUN ./mvnw clean install -DskipTests

# Create a minimal runtime image
FROM adoptopenjdk/openjdk17:alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
