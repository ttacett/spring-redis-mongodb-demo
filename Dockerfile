FROM eclipse-temurin:23-jdk AS builder
WORKDIR /app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
RUN chmod +x mvnw
# Adding -e flag for detailed error output and ensuring annotation processing is enabled
RUN ./mvnw clean package -DskipTests -e -Dmaven.compiler.annotationProcessors.enable=true

FROM eclipse-temurin:23-jre
WORKDIR /app
COPY --from=builder /app/target/task_management-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]