FROM openjdk:jre-slim-stretch
EXPOSE 5000

ADD /target/authservice*.jar authservice.jar

ENTRYPOINT ["java", "-jar", "authservice.jar"]