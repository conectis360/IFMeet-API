FROM openjdk:8-jre-alpine

# Set timezone and update packages
RUN mkdir /app && \
    rm -f /etc/localtime && \
    ln -s /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime && \
    apk upgrade --no-cache && \
    adduser -D -H ifmeet

WORKDIR /app

# Copy the JAR file (make sure the name matches the build output)
COPY ${JAR_FILE} app.jar

# Set Spring profile to production via ARG and ENV
ARG SPRING_PROFILE
ENV SPRING_PROFILES_ACTIVE=${SPRING_PROFILE}

# Expose the application port
EXPOSE 9000

# Set Java options via ARG and ENV
ARG JAVA_OPTS
ENV JAVA_OPTS=${JAVA_OPTS}

# Run the application (use the correct JAR name)
ENTRYPOINT ["java", "${JAVA_OPTS}", "-jar", "app.jar"]