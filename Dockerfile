FROM eclipse-temurin:25-jdk AS build

WORKDIR /app

# Copy only what Maven needs first (for caching)
COPY mvnw pom.xml ./
COPY .mvn .mvn
RUN chmod +x mvnw
RUN ./mvnw -B dependency:go-offline

# Copy source and build
COPY src src
RUN ./mvnw -B package -DskipTests

# Runtime stage
FROM eclipse-temurin:25-jdk

WORKDIR /app

COPY --from=build /app/target/*SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]