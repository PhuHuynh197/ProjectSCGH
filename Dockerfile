FROM eclipse-temurin:17-jdk-alpine
COPY target/*.jar /app/app.jar
WORKDIR /app
CMD ["java", "-jar", "app.jar"]
