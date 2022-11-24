FROM openjdk:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY $JAR_FILE build/libs/*.jar app.jar
EXPOSE 9090
cmd ["java", "-jar", "app.jar"]



