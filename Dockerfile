FROM openjdk:17-alpine
COPY ./build/libs/*.jar /deploy/jar/backend.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar", "/deploy/jar/backend.jar"]
