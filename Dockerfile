FROM openjdk:17-alpine
COPY ./build/libs/*.jar /deploy/jar/backend.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=prod", "/deploy/jar/backend.jar"]
