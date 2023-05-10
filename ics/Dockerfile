# syntax=docker/dockerfile:1

FROM eclipse-temurin:17-jdk-jammy

COPY .ics/target/ics-0.0.1-SNAPSHOT.jar /ics/app.jar

EXPOSE 8080

CMD ["java", "-jar", "/ics/app.jar"]
