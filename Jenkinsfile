pipeline {
    agent any

    triggers {
        githubPush()
    }

    stages {
        stage('Get the code from Github') {
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