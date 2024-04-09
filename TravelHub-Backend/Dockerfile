FROM adoptopenjdk/openjdk17:alpine-jre
ADD target/travelhub.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
