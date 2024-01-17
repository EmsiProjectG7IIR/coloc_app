FROM openjdk:17-alpine

WORKDIR /app

COPY target/eureka_server-0.0.1-SNAPSHOT.jar /app/eureka-service.jar

EXPOSE 8761

CMD ["java", "-jar", "/app/eureka-service.jar"]
