FROM openjdk:8-jre-alpine
WORKDIR /app
COPY *.jar app.jar
ARG SPRING_PROFILE
ENV SPRING_PROFILES_ACTIVE=${SPRING_PROFILE}
EXPOSE 9000
ENTRYPOINT ["java", "-Xms512m", "-Xmx1024m", "-jar", "/app/app.jar"]