# Stage 1: Build Angular application
FROM node:latest as builder

# Set working directory for Angular app
WORKDIR /app

# Copy package.json and package-lock.json
COPY TravelHub-frontend/package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application source code
COPY TravelHub-frontend .

# Build Angular application
RUN npm run build --prod

# Stage 2: Build Spring Boot application
FROM openjdk:17-jdk-alpine as backend

# Set working directory for Spring Boot app
WORKDIR /app

# Copy Angular build output to Nginx directory
COPY --from=builder /app/dist/travel-hub-frontend /usr/share/nginx/html

# Add the compiled JAR file from the target directory to the Docker image
COPY TravelHub-Backend/target/travelhub.jar app.jar

# Specify the entry point command to run the Spring Boot application when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]
