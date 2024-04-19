# Stage 1: Build Angular application
FROM node:latest as builder

# Set working directory
WORKDIR /app

# Copy package.json and package-lock.json
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application source code
COPY . .

# Build Angular application
RUN npm run build --prod

# Stage 2: Build Spring Boot application
FROM openjdk:17-jdk-alpine as backend

# Add the compiled JAR file from the target directory to the Docker image
COPY --from=builder /app/dist/travel-hub-frontend /usr/share/nginx/html

# Set working directory for Spring Boot app
WORKDIR /app

# Add the compiled JAR file from the target directory to the Docker image
ADD target/travelhub.jar app.jar

# Specify the entry point command to run the Spring Boot application when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]
