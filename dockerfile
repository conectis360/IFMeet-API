FROM openjdk:8-jre-alpine

RUN mkdir /app && rm -f /etc/localtime && ln -s /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime && apk upgrade --no-cache && adduser -D -H ifmeet

WORKDIR /app

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o arquivo JAR gerado pelo Maven ou Gradle para dentro do contêiner
COPY target/*.jar app.jar

# Define a variável de ambiente para rodar no perfil PRD
ENV SPRING_PROFILES_ACTIVE=prd

# Expõe a porta 9000 para acesso externo
EXPOSE 9000

# Comando para rodar a aplicação
CMD ["java", "-jar", "minha-api.jar"]