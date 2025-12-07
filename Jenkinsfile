pipeline {
    agent any

    stages {
        stage('Get the code from Github') {
            steps {
                git branch: 'main',
                    url: 'git@github.com:osmarsantosjr/osmarspetitions.git',
                    credentialsId: '0077138b-463a-4362-800c-6ad883ebf67d'
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

        stage('Archive Artifact') {
            steps {
//                 sh 'mvn package -DskipTests'
                // Archive the generated WAR file for future reference
                archiveArtifacts artifacts: 'target/*.war', fingerprint: true
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                // Example: copy WAR file to remote Tomcat server via SCP
                sh '''
                scp -i /home/jenkins/.ssh/deploy_key \
                    target/osmarspetitions.war \
                    ec2-user@SEU_SERVIDOR:/opt/tomcat/webapps/
                '''
            }
        }
    }

    post {
        always {
            // Publish JUnit test reports
            junit 'target/surefire-reports/*.xml'
        }
        success {
            echo 'Build and deployment completed successfully!'
        }
        failure {
            echo 'Build failed. Please check logs.'
        }
    }
}
