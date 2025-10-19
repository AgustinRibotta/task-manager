FROM maven:3.9.3-eclipse-temurin-20 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/*.jar task-manager.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","task-manager.jar"]
