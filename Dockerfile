FROM openjdk:17-jdk-alpine
ARG JAR=/target/simplewebapp.jar
COPY ${JAR} simplewebapp.jar
ENTRYPOINT ["java","-jar","/salary-distribution-service-0.0.1-SNAPSHOT.jar"]