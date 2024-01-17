FROM openjdk:17-alpine

WORKDIR /app

COPY target/offre_service-0.0.1-SNAPSHOT.jar /app/offre-service.jar

EXPOSE 8087

CMD ["java", "-jar", "/app/offre-service.jar"]
