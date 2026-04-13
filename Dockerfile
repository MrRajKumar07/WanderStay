# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run the application
# Use Eclipse Temurin (highly stable and replaces the old openjdk image)
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy the jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Render uses a dynamic port
ENTRYPOINT ["sh", "-c", "java -Dserver.port=${PORT} -jar app.jar"]