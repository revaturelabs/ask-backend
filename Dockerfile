FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ./target/ask-0.0.1-SNAPSHOT.jar ask-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","ask-0.0.1-SNAPSHOT.jar"]
EXPOSE 1337/tcp