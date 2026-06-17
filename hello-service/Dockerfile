FROM eclipse-temurin:21-jdk
LABEL authors="Keerthi"
WORKDIR /app
COPY target/hello-service-0.0.1-SNAPSHOT.jar hello-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar","hello-service.jar"]