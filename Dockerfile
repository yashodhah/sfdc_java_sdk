FROM openjdk:17-alpine
MAINTAINER yashodha.dev.space
COPY target/bo-sync-poc.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]