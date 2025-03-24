FROM openjdk:8-jre-alpine

# Set timezone and update packages
RUN mkdir /app && \
    rm -f /etc/localtime && \
    ln -s /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime && \
    apk upgrade --no-cache && \
    adduser -D -H ifmeet

WORKDIR /app

# Copy the JAR file (make sure the name matches the build output)
COPY target/*.jar app.jar

# Set Spring profile to production
ENV SPRING_PROFILES_ACTIVE=prd

# Expose the application port
EXPOSE 9000

# Run the application (use the correct JAR name)
CMD ["java", "-jar", "app.jar"]