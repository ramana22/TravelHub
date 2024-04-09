FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/travelhub.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
