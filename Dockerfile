FROM maven:3.8.3-openjdk-17 AS builder
WORKDIR /sfdc-sync-poc
#RUN --mount=type=cache,target=~/.m2/repository \
#    --mount=type=bind,target=.  \
#    mvn clean package \

#RUN --mount=type=bind,target=. \
#    chmod -R 777 target \
#    && mvn clean package

FROM openjdk:17-alpine AS main
COPY --from=builder target/bo-sync-poc.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]