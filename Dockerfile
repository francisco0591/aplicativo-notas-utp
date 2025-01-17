FROM openjdk:24-slim-bullseye
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} aplicacion-notas-utp-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/aplicacion-notas-utp-0.0.1-SNAPSHOT.jar"]