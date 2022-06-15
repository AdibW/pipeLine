# FROM  openjdk:11
# EXPOSE 8083
# ADD /target/evaluation-service-0.0.1-SNAPSHOT.jar evaluation-service.jar
# ENTRYPOINT ["java", "-jar", "evaluation-service.jar"]

FROM maven:3.52.0-jdk-11-slim as builder

RUN mkdir -p /home/evaluationService/back

COPY ./evalulation-service/ /home/evaluationService/back

WORKDIR /home/evaluationService/back
# Compile and package the application to an executable JAR
RUN mvn install -Dmaven.test.skip=true

# For Java 11,

FROM adoptopenjdk/openjdk11:alpine-jre as runtime

COPY --from=builder /home/evaluationService/back/target/*.jar /home/evaluationService/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/home/evaluationService/app.jar"]
