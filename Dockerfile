FROM openjdk:17-alpine
COPY ./build/libs/*.jar backend.jar
EXPOSE 8080
ENTRYPOINT ["java", "--spring.profiles.active=prod","-jar", "backend.jar"]
