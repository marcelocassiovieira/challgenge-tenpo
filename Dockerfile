
FROM openjdk:11-jre-slim

COPY target/Challenge_Tenpo-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8001

CMD ["java", "-jar", "/app/app.jar"]
