pipeline {
    agent any

    tools {
        git 'Git_2.50.1'
    }


    stages {
        stage('Get the code from Github') {
            steps {
                git branch: 'main',
                    url: 'git@github.com:osmarsantosjr/osmarspetitions.git',
                    credentialsId: 'github-ssh-key'
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

        stage('Package WAR') {
            steps {
                sh 'mvn -B package'
                sh 'mv target/*.war target/osmarspetitions.war'
                archiveArtifacts artifacts: 'target/osmarspetitions.war', fingerprint: true
            }
        }

        stage('Approval') {
            steps {
                timeout(time: 10, unit: 'MINUTES') {
                    input message: 'Deploy to EC2?'
                }
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                // Example: copy WAR file to remote Tomcat server via SCP
                sh '''
                scp -i /home/jenkins/.ssh/deploy_key \
                    target/osmarspetitions.war \
                    ec2-user@56.228.26.152:/opt/tomcat/webapps/
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
