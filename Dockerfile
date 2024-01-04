FROM openjdk:17-alpine
COPY ./build/libs/*.jar backend.jar
COPY src/main/resources/application.yml application.yml
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.config.location=classpath:/application.yml","-jar", "backend.jar"]
