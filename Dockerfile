# Stage 1: Build the application
# Using Maven to build the jar
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the jar from the build stage
# Update 'WanderStay-0.0.1-SNAPSHOT.jar' if your artifact name is different
COPY --from build /app/target/*.jar app.jar

# Render uses a dynamic port, so we map it to Spring's server port
ENTRYPOINT ["sh", "-c", "java -Dserver.port=${PORT} -jar app.jar"]