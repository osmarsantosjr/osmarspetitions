pipeline {
    agent any
    
    triggers {
        githubPush()
    }

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
        
        stage ('Exec') {
            steps {
                sh 'mvn exec:java'
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
    }
}
