pipeline {
    agent any

    tools {
        maven 'Maven_3.9'   // Name of the Maven installation configured in Jenkins
        jdk 'JDK17'         // Name of the JDK installation configured in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                // Clone the repository from GitHub using SSH key
                git branch: 'main',
                    url: 'git@github.com:seuusuario/osmarspetitions.git',
                    credentialsId: 'github-ssh-key'
            }
        }

        stage('Build') {
            steps {
                // Compile and package the application, skipping tests
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                // Run unit tests
                sh 'mvn test'
            }
        }

        stage('Archive Artifact') {
            steps {
                // Archive the generated WAR file for future reference
                archiveArtifacts artifacts: 'target/osmarspetitions.war', fingerprint: true
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
