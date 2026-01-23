# Estágio de Build
FROM maven:3.9.6-eclipse-temurin-21 AS build
COPY . .
RUN chmod +x mvnw && ./mvnw clean install -DskipTests

# Estágio de Execução
FROM eclipse-temurin:21-jre-jammy
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]