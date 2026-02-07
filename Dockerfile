# 1) Build stage  - compile and build jar
FROM gradle:jdk17 AS build

WORKDIR /home/gradle/src
COPY --chown=gradle:gradle . .

RUN gradle clean bootJar --no-daemon

# 2) Run Stage - run jar 

FROM eclipse-temurin:17-jre-alpine

WORKDIR /app
COPY --from=build /home/gradle/src/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "app.jar" ]