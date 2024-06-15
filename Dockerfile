FROM maven:3.8.8-eclipse-temurin-21-alpine AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-slim
COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar rectangles.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "rectangles.jar"]
