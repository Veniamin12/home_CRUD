FROM maven:3.8.4 AS build
COPY ../src /app/src
COPY ../pom.xml /app
RUN mvn -q -f /app/pom.xml clean package -DskipTests

#Use official image java for launch app
FROM  openjdk:17-jdk-alpine
ARG JAR_FILE=/app/target/*.jar
COPY --from=build ${JAR_FILE} app.jar
CMD ["java", "-jar", "app.jar"]
