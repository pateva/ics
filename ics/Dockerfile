FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY .mvn/ .mvnCOPY mvnw pom.xml ./RUN ./mvnw dependency:resolve
COPY src ./srcCMD ["./mvnw", "spring-boot:run"]
