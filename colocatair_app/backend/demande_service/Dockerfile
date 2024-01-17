FROM openjdk:17-alpine

WORKDIR /app

COPY target/demande_service-0.0.1-SNAPSHOT.jar /app/demande-service.jar

EXPOSE 8082

CMD ["java", "-jar", "/app/demande-service.jar"]
