pipeline {
    agent any

    stages {
        stage('Get the code from Github') {
            steps {
                git branch: 'main',
                    url: 'git@github.com:osmarsantosjr/osmarspetitions.git'
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

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Please check the logs.'
        }
    }
}
