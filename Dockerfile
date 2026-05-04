# Build stage
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

# Copia o código fonte
COPY . .

# Compila o projeto
RUN ./mvnw clean package -DskipTests --no-transfer-progress

# Runtime stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copia o JAR gerado
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]