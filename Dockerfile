# Use the official OpenJDK 17 image as the base image for Spring Boot
FROM openjdk:17-jdk-alpine as backend

# Set working directory for Spring Boot app
WORKDIR /app

# Add the compiled JAR file from the target directory to the Docker image
COPY TravelHub-Backend/target/travelhub.jar app.jar

# Specify the entry point command to run the Spring Boot application when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]

# Use the official Node.js image as the base image for Angular
FROM node:latest as frontend

# Set working directory for Angular app
WORKDIR /app

# Copy the Angular application source code to the working directory
COPY TravelHub-frontend .

# Install dependencies and build Angular application
RUN npm install
RUN npm run build --prod

# Use NGINX image as the base image for serving Angular static files
FROM nginx:alpine

# Copy NGINX configuration
COPY TravelHub-frontend/src/nginx/etc/conf.d/default.conf /etc/nginx/conf.d/default.conf

# Copy built Angular application files to NGINX HTML directory
COPY TravelHub-frontend/dist/travel-hub-frontend /usr/share/nginx/html

