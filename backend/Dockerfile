FROM maven:3.8.1-openjdk-17-slim as base

COPY pom.xml .

RUN ["mvn", "dependency:go-offline", "--no-transfer-progress"]

COPY src src

EXPOSE 8080

CMD ["mvn", "spring-boot:run"]
