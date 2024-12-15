FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml /app
RUN mvn dependency:go-offline
COPY src /app/src
RUN mvn clean install -DskipTests

FROM eclipse-temurin:17-jre-alpine

COPY --from=build /app/target/*.jar /app.jar

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "/app.jar"]