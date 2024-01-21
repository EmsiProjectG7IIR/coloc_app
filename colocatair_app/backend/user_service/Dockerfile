FROM openjdk:17-alpine

WORKDIR /app

COPY target/user_service-0.0.1-SNAPSHOT.jar /app/user-service.jar

EXPOSE 8088

CMD ["java", "-jar", "/app/user-service.jar"]