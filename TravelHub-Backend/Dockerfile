# Stage 1: Backend (Spring Boot)
FROM openjdk:8 AS backend
EXPOSE 8080
ADD TravelHub-Backend/target/travelhub.jar /travelhub.jar
ENTRYPOINT ["java","-jar","/travelhub.jar"]

# Stage 2: Frontend (Angular)
FROM nginx:alpine AS frontend
COPY TravelHub-frontend/dist /usr/share/nginx/html
EXPOSE 80

