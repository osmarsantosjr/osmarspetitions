pipeline {
    agent any

    stages {
        stage('Get the code from Github') {
            steps {
                git branch: 'main',
                    credentialsId: '0077138b-463a-4362-800c-6ad883ebf67d',
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

        stage('Package and archive') {
            steps {
                sh 'mvn package -DskipTests'
                archiveArtifacts artifacts: 'target/osmarspetitions.war', fingerprint: true
            }
        }

        stage('Deploy to Tomcat (manual approval)') {
            steps {
                script {
                    timeout(time: 5, unit: 'MINUTES') {
                        input message: "Do you want to deploy osmarspetitions.war to Tomcat on AWS EC2?"
                    }
                    sh '''
                        scp -o StrictHostKeyChecking=no target/osmarspetitions.war ec2-user@ec2-13-61-27-221.eu-north-1.compute.amazonaws.com:/opt/tomcat/webapps/
                        ssh ec2-user@ec2-13-61-27-221.eu-north-1.compute.amazonaws.com "sudo systemctl restart tomcat"
                    '''
                }
            }
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
