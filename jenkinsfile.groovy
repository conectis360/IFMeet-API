pipeline {
    agent any

    environment {
        REPO_URL = 'https://github.com/conectis360/IFMeet-API.git'  // 🔹 Seu repositório Git
        BRANCH = 'master'  // 🔹 Branch a ser usado

        JAR_NAME = 'ifmeet-api.jar'  // 🔹 Nome do arquivo gerado pelo Maven
        APP_NAME = 'ifmeet-api'  // 🔹 Nome da aplicação

        DOCKER_IMAGE = 'ifmeet-api'  // 🔹 Nome da imagem Docker
        DOCKER_CONTAINER = 'ifmeet-api-container'  // 🔹 Nome do container
        SERVER_PORT = '9000'  // 🔹 Porta da aplicação

        JAVA_OPTS = '-Xms512m -Xmx1024m'  // 🔹 Configuração da JVM
        SPRING_PROFILE = 'prd'  // 🔹 Profile do Spring Boot
    }

    stages {
        stage('📥 Checkout do Código') {
            steps {
                git branch: BRANCH, url: REPO_URL
            }
        }

        stage('🔨 Build do Projeto Java') {
            steps {
                script {
                    bat 'mvn clean install package -DskipTests'  // Windows CMD
                }
            }
        }

        stage('📦 Preparar Artefato para Docker') {
            steps {
                script {
                    // Move o JAR para um local adequado para o build do Docker
                    bat "move target\\*.jar ${JAR_NAME}"
                }
            }
        }

        stage('🐳 Build da Imagem Docker') {
            steps {
                script {
                    bat """
                        docker build -t ${DOCKER_IMAGE} --build-arg JAR_FILE=${JAR_NAME} .
                    """
                }
            }
        }

        stage('🛑 Parar e Remover Container Antigo') {
            steps {
                script {
                    bat "docker stop ${DOCKER_CONTAINER} || exit 0"
                    bat "docker rm ${DOCKER_CONTAINER} || exit 0"
                }
            }
        }

        stage('🚀 Subir Novo Container') {
            steps {
                script {
                    bat """
                        docker run -d --name ${DOCKER_CONTAINER} -p ${SERVER_PORT}:${SERVER_PORT} \\
                        -e SPRING_PROFILES_ACTIVE=${SPRING_PROFILE} \\
                        -e JAVA_OPTS="${JAVA_OPTS}" ${DOCKER_IMAGE}
                    """
                }
            }
        }
    }

    post {
        success {
            echo '🎉 Deploy realizado com sucesso!'
        }
        failure {
            echo '❌ Falha no deploy!'
        }
    }
}
