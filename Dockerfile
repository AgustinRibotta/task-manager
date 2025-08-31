FROM openjdk:17

WORKDIR /

COPY target/*.jar task-manager.jar

ENTRYPOINT ["java", "-jar", "task-manager.jar"]

EXPOSE 8080
