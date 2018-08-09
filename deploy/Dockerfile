# Work in progress, not functioning yet
#creates an image from executable jar

FROM frolvlad/alpine-oraclejdk8:latest
MAINTAINER mohamed.abdou79@gmail.com
EXPOSE 8080
ADD spring-boot-app-service-example.jar /app/jar-file-name.jar

ENTRYPOINT java -jar /app/jar-file-name.jar --server.port=8080
