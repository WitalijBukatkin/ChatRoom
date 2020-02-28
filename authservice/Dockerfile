FROM openjdk:jre-slim-stretch
EXPOSE 5000

ADD /target/authservice*.jar authservice.jar

ENTRYPOINT sleep 30 && java -jar authservice.jar