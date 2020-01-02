FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY $JAR_FILE ask-backend.jar
CMD ["java","-jar", "ask-backend.jar"]
EXPOSE 1337/tcp
