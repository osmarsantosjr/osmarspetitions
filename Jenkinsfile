pipeline {
    agent any
    
    // tools {
    //     maven 'Maven-3.9.6'   // Nome da instalação configurada no Jenkins (Gerenciar Jenkins → Ferramentas)
    //     jdk 'JDK-17'          // Nome da instalação do JDK configurada no Jenkins
    // }

    stages {
        stage('GetProject') {
            steps {
                git branch: 'main', url: 'https://github.com/osmarsantosjr/osmarspetitions.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean:clean'
                sh 'mvn dependency:copy-dependencies'
                sh 'mvn compiler:compile'
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
           archiveArtifacts allowEmptyArchive: true, 
           artifacts: '**/first_maven*.jar'
        }

            
        //     echo 'Build e testes concluídos com sucesso!'
        // }
        // failure {
        //     echo 'Pipeline falhou. Verifique os logs.'
    }
}
