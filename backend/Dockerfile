FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom first to allow Docker cache for dependencies
COPY pom.xml .

# Copy source and build
COPY src ./src
RUN mvn -B -DskipTests package

FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copy jar produced by Maven build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENV JAVA_OPTS="-Xms256m -Xmx512m"

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
