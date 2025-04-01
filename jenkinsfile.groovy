pipeline {
    agent any

    environment {
        DOCKER_IMAGE_NAME = "ifmeet-api"              // Nome da imagem Docker
        DOCKER_CONTAINER_NAME = "api-container"       // Nome do container
        DOCKERFILE_PATH = "Dockerfile"                // Caminho para o Dockerfile
        SPRING_PROFILE = "prd"                        // Profile do Spring Boot para produção
        JAVA_OPTS = "-Xms512m -Xmx1024m"              // Opções da JVM para produção
        SERVER_PORT = "9000"                          // Porta que a aplicação expõe
        DOCKER_REGISTRY = "docker.io/usuario/ifmeet-api" // Substitua pelo seu repositório DockerHub/GitHub/Private Registry
    }

    stages {
        stage('Checkout') {
            steps {
                git(url: 'https://github.com/conectis360/IFMeet-API.git', branch: 'master') // Clona o repositório
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Construir a imagem no Windows (usando `bat`)
                    bat """
                        docker build -t %DOCKER_IMAGE_NAME%:latest ^
                            --build-arg SPRING_PROFILE=%SPRING_PROFILE% ^
                            --build-arg JAVA_OPTS="%JAVA_OPTS%" ^
                            -f %DOCKERFILE_PATH% .
                    """
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    // Faz login no DockerHub (se necessário)
                    bat "docker login -u %DOCKER_USERNAME% -p %DOCKER_PASSWORD%"

                    // Marca a imagem com o repositório remoto
                    bat "docker tag %DOCKER_IMAGE_NAME%:latest %DOCKER_REGISTRY%:latest"

                    // Faz push da imagem para o registro
                    bat "docker push %DOCKER_REGISTRY%:latest"
                }
            }
        }

        stage('Stop and Remove Existing Container') {
            steps {
                script {
                    // Para e remove qualquer container em execução com o mesmo nome
                    bat "docker stop %DOCKER_CONTAINER_NAME% || exit 0"
                    bat "docker rm %DOCKER_CONTAINER_NAME% || exit 0"
                }
            }
        }

        stage('Run Docker Container') {
            steps {
                script {
                    // Puxa a versão mais recente da imagem antes de rodar o container
                    bat "docker pull %DOCKER_REGISTRY%:latest"

                    // Executa o novo container
                    bat """
                        docker run -d ^
                            --name %DOCKER_CONTAINER_NAME% ^
                            -p %SERVER_PORT%:%SERVER_PORT% ^
                            -e SPRING_PROFILES_ACTIVE=%SPRING_PROFILE% ^
                            -e JAVA_OPTS="%JAVA_OPTS%" ^
                            %DOCKER_REGISTRY%:latest
                    """
                }
            }
        }
    }

    post {
        always {
            cleanWs() // Limpa o workspace após a execução
        }
    }
}
