# For Java 8 open jdk, try this
FROM adoptopenjdk/openjdk11:alpine-jre

#Copy target/vanigam-api.jar to build context
COPY target/gitfocus-service.jar gitfocus-service.jar
EXPOSE 8888
# java -jar vanigam-api.jar
ENTRYPOINT ["java","-jar","gitfocus-service.jar"]
