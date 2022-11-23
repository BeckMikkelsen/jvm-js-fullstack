FROM openjdk:17-jdk-alpine

COPY build/libs/*.jar app.jar
EXPOSE 9090
cmd ["java", "-jar", "app.jar"]



