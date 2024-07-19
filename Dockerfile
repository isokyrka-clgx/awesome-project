FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/project-0.0.1-SNAPSHOT.jar /app/demo.jar

ENTRYPOINT ["java", "-jar", "demo.jar"]