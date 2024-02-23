pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Explicitly checkout the code from the repository and set the branch
                checkout([$class: 'GitSCM', branches: [[name: 'main']], userRemoteConfigs: [[url: 'https://github.com/RadwaNagib/Qweira_App.git']]])
            }
        }

        stage('Build') {
            steps {
                // Build the Maven project
                sh 'mvn clean install'
            }
        }

        stage('Run Tests') {
            steps {
                // Run your Maven project tests
                sh 'mvn test'
            }
        }
    }
}

