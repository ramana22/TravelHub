# Stage 1: Backend (Spring Boot)
FROM openjdk:8 AS backend
EXPOSE 8080
ADD TravelHub-Backend/target/travelhub.jar /travelhub.jar
ENTRYPOINT ["java","-jar","/travelhub.jar"]



