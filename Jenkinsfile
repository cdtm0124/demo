pipeline {
    agent any

    tools {
        git 'Default'
    }

    stages {
        stage('GetProject') {
            steps {
                git branch: 'main', url: 'https://github.com/cdtm0124/demo.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }
        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }
        stage('Archive') {
            steps {
                archiveArtifacts allowEmptyArchive: true, artifacts: 'target/*.war'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Building Docker image...'
                sh 'docker build -f Dockerfile -t myapp .'

                echo 'Removing old Docker container if it exists...'
                sh 'docker rm -f "myappcontainer" || true'

                echo 'Starting Docker container...'
                sh 'docker run --name "myappcontainer" -p 8081:8080 --detach myapp:latest'
            }
        }
    }
}
