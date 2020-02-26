### Chatroom as a messager, allowing text messaging to peaple within the network 

### Uses
- Docker
- Java 11
- SpringBoot 2
- Spring Security with OAuth2
- PostgreSQL
- Adminer
- RabbitMQ
- Junit 5
- Thymeleaf
- Bootstrap
- Jquery and Noty
- StompJs

## Consist of
- messageservice - implemented messagebase and has restapi
- authservice - stored users and tokens
- chatroomservice - central service has web pages and ajax controllers for management messages and chats

#### You can start it your computer with linux

Requirement packages:
 - docker and docker-compose
	- You can install it for debian-based using:
	```sudo apt-get update && sudo apt install docker docker-compose```
 - openjdk11 and maven
	- You can install it for debian-based using:
	```sudo apt-get update && sudo apt install openjdk-11-jdk maven```
	- And sets $JAVAHOME
	```export JAVA_HOME=/usr/lib/jvm/default-java```


2. Use this command for start:
```
git clone https://github.com/WitalijBukatkin/ChatRoom
unzip ChatRoom.zip
cd ChatRoom
mvn package
sudo mvn docker-compose:up
```
3. Find out you local ip with `hostname -I`
and type this in you Web Browser as {ip}:8080 for example: 192.168.1.70:8080

4. Register and login in
5. Create chat (maybe can update page), open and waiting your companion
6. When he comes in you can talk to him