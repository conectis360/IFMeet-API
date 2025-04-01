FROM openjdk:8-jre-alpine
WORKDIR /app
COPY *.jar app.jar
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "/app/app.jar"]