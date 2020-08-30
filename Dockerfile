FROM openjdk:8-jdk-alpine
MAINTAINER Sunil Rai <sunilrai7607@gmail.com>
VOLUME /app
ARG version
ENV version_number=$version
COPY ./build/libs/springboot-kafka-listener-$version_number.jar springboot-kafka-listener.jar
ENTRYPOINT ["java", "-jar","/springboot-kafka-listener.jar"]