#Construcción
FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

#Imagen final
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Puerto
EXPOSE 8080

# Comando
ENTRYPOINT ["java", "-jar", "app.jar"]
