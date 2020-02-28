FROM openjdk:jre-slim-stretch
EXPOSE 8080

ADD /target/chatroomservice*.jar chatroomservice.jar

ENTRYPOINT sleep 30 && java -jar chatroomservice.jar