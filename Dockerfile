FROM openjdk:17-alpine
COPY ./build/libs/*.jar /app/backend.jar
WORKDIR /app
COPY src/main/resources/application.yml app/src/main/resources/application.yml
EXPOSE 8080
ENTRYPOINT ["java","-jar", "/app/backend.jar"]
