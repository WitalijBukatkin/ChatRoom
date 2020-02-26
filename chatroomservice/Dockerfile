FROM openjdk:jre-slim-stretch
EXPOSE 8080

ADD /target/chatroomservice*.jar chatroomservice.jar

ENTRYPOINT ["java", "-jar", "chatroomservice.jar"]