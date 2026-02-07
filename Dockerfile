# ===== Build Stage =====
FROM eclipse-temurin:17-jdk AS build

WORKDIR /app

# Copy everything
COPY . .

# Give execute permission to gradlew
RUN chmod +x gradlew

# Build jar
RUN ./gradlew clean bootJar --no-daemon

# ===== Run Stage =====
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
