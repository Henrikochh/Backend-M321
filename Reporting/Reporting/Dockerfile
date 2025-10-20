FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/*.jar reporting.jar

EXPOSE 8084

ENTRYPOINT ["java", "-jar", "reporting.jar"]