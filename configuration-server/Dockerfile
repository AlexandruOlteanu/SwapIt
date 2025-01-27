FROM openjdk:22-jdk-slim
WORKDIR /app
COPY target/configuration-server-0.0.1-SNAPSHOT.jar /app/configuration-server.jar
COPY ../configuration-data /app/configuration-data
CMD ["java", "-jar", "/app/configuration-server.jar"]
