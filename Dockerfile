FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} /build/libs/shoppingList-js-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/build/libs/shoppingList-js-1.0-SNAPSHOT.jar"]