# syntax=docker/dockerfile:1

FROM eclipse-temurin:17-jdk-jammy

ARG DIR_ICS
# The /app directory should act as the main application directory
RUN ls -al

#COPY --chown=1000:1000 ${TEST}/. /ics/
WORKDIR /ics/ics
RUN ls -al

COPY ics/.mvn/. /.mvn/
COPY ics/mvnw ics/pom.xml ./

RUN /ics/mvnw dependency:resolve

# Copy local directories to the current local directory of our docker image (/ics)
COPY src ./src

EXPOSE 8080

CMD ["./mvnw", "spring-boot:run"]


