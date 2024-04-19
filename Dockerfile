# Stage 1: Build Angular application
FROM node:latest as frontend

# Set working directory for Angular app
WORKDIR /app/TravelHub-frontend

# Copy package.json and package-lock.json
COPY TravelHub-frontend/package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the Angular application source code
COPY TravelHub-frontend .

# Build Angular application
RUN npm run build --prod

# Stage 2: Build and run Spring Boot application
FROM openjdk:17-jdk-alpine as backend

# Set working directory for Spring Boot app
WORKDIR /app/TravelHub-Backend

# Copy the Spring Boot application JAR file
COPY TravelHub-Backend/target/travelhub.jar .

# Expose Spring Boot app port
EXPOSE 8080

# Run Spring Boot application
CMD ["java", "-jar", "travelhub.jar"]
