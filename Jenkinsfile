
pipeline {
    agent any

    tools {
        maven 'Maven-3.9.6'   // Nome da instalação configurada no Jenkins (Gerenciar Jenkins → Ferramentas)
        jdk 'JDK-17'          // Nome da instalação do JDK configurada no Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/osmarsantosjr/petitions-springboot.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }
    }

    post {
        success {
            echo 'Build e testes concluídos com sucesso!'
        }
        failure {
            echo 'Pipeline falhou. Verifique os logs.'
        }
    }
}
