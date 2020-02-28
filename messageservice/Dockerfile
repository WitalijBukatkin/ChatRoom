FROM openjdk:jre-slim-stretch
EXPOSE 8081

ADD /target/messageservice*.jar messageservice.jar

ENTRYPOINT sleep 20 && java -jar messageservice.jar