pipeline {
    agent any

    environment {
        DOCKER_IMAGE_NAME = "ifmeet-api" // Substitua pelo nome da sua imagem Docker de produção
        DOCKER_CONTAINER_NAME = "api-container"        // Nome do container que será criado
        DOCKERFILE_PATH = "Dockerfile"                 // Caminho para o seu Dockerfile
        SPRING_PROFILE = "prd"                          // Profile do Spring Boot para produção
        JAVA_OPTS = "-Xms512m -Xmx1024m"                // Opções da JVM para produção
        SERVER_PORT = "9000"                             // Porta que a aplicação expõe
    }

    stages {
        stage('Checkout') {
            steps {
                git(url: 'https://github.com/conectis360/IFMeet-API.git', branch: 'master') // Substitua pela URL do seu repositório
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Construir a imagem Docker com o profile de produção
                    def image = dockerImageBuild(
                            dockerfile: DOCKERFILE_PATH,
                            buildArgs: ["SPRING_PROFILE=${SPRING_PROFILE}", "JAVA_OPTS=${JAVA_OPTS}"],
                            imageName: DOCKER_IMAGE_NAME,
                            pull: true // Opcional: Tenta puxar a imagem base mais recente
                    )
                    // Fazer push da imagem para o registro Docker (opcional, mas comum em produção)
                    dockerImagePush(image: image)
                }
            }
        }

        stage('Stop and Remove Existing Container (if any)') {
            steps {
                script {
                    // Parar o container existente, ignorando erro se não existir
                    sh "docker stop ${DOCKER_CONTAINER_NAME} || true"
                    // Remover o container existente, ignorando erro se não existir
                    sh "docker rm ${DOCKER_CONTAINER_NAME} || true"
                }
            }
        }

        stage('Run Docker Container') {
            steps {
                script {
                    // Executar o novo container com o profile de produção
                    sh """
                        docker run -d \
                            --name ${DOCKER_CONTAINER_NAME} \
                            -p ${SERVER_PORT}:${SERVER_PORT} \
                            -e SPRING_PROFILES_ACTIVE=${SPRING_PROFILE} \
                            -e JAVA_OPTS="${JAVA_OPTS}" \
                            ${DOCKER_IMAGE_NAME}
                    """
                }
            }
        }
    }

    post {
        always {
            cleanWs() // Limpar o workspace
        }
    }
}