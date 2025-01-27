FROM openjdk:22-jdk
WORKDIR /app

COPY ./user/user-service/target/user-service-0.0.1-SNAPSHOT-exec.jar /app/user-service.jar
COPY ./commons/target/commons-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "/app/user-service.jar"]
