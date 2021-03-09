# For Java 8 open jdk, try this
FROM adoptopenjdk/openjdk11:alpine-jre


COPY target/gitfocus-service.jar gitfocus-service.jar

EXPOSE 4041

ENTRYPOINT ["java","-jar","gitfocus-service.jar"]
