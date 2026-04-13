# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:17-jdk-slim
WORKDIR /app

# FIXED: Explicitly naming the source stage with '=' to avoid parse errors
COPY --from=build /app/target/*.jar app.jar

# Render uses a dynamic port, so we map it to Spring's server port
ENTRYPOINT ["sh", "-c", "java -Dserver.port=${PORT} -jar app.jar"]