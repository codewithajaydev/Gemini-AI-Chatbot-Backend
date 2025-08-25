# Stage 1: Build
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080

# Environment variables for Gemini
ENV GEMINI_API_URL=""
ENV GEMINI_API_KEY=""

ENTRYPOINT ["java", "-jar", "app.jar"]
