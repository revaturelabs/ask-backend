FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY /var/lib/jenkins/workspace/ask-backend/target/ask-0.0.1-SNAPSHOT.jar ask-backend.jar
CMD ["java","-jar", "ask-backend.jar"]
EXPOSE 1337/tcp
