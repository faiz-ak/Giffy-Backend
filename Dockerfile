# ---------- Build stage ----------
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy only project folder
COPY Giffy/pom.xml .
COPY Giffy/.mvn .mvn
COPY Giffy/mvnw .
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

COPY Giffy/src src
RUN ./mvnw clean package -DskipTests

# ---------- Run stage ----------
FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
