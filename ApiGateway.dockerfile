FROM openjdk:22-jdk
WORKDIR /app
COPY ./apiGateway/apiGateway-service/target/apiGateway-service-0.0.1-SNAPSHOT-exec.jar /app/apiGateway-service.jar

COPY ./user/user-service/target/user-service-0.0.1-SNAPSHOT.jar .
COPY ./product/product-service/target/product-service-0.0.1-SNAPSHOT.jar .
COPY ./chat/chat-service/target/chat-service-0.0.1-SNAPSHOT.jar .
COPY ./searchEngine/searchEngine-service/target/searchEngine-service-0.0.1-SNAPSHOT.jar .
COPY ./commons/target/commons-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "/app/apiGateway-service.jar"]
