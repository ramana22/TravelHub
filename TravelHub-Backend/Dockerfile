# Use the AdoptOpenJDK JDK 17 Alpine JRE image as the base image
FROM adoptopenjdk/openjdk11:alpine-jre

# Add the compiled JAR file from the target directory to the Docker image
ADD target/travelhub.jar app.jar

# Specify the entry point command to run the Spring Boot application when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]
