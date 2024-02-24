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
                bat "${MAVEN_HOME}\\bin\\mvn clean install"
            }
        }

         stage('Run Tests') {
            steps {
                // Assuming your tests are testNG tests
               bat "${MAVEN_HOME}\\bin\\mvn test -Dtestng.file=regression.xml"
            }
        }
    }
}

