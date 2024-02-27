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
               bat "\"${MAVEN_HOME}\\bin\\mvn\" clean install"
            }
        }
  stage('Build1') {
            steps {
                bat 'make' 
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true 
            }
        }
         stage('Run Tests') {
            steps {
                // Assuming your tests are testNG tests(run tests)
               bat "${MAVEN_HOME}\\bin\\mvn test   Dtestng.file=regression.xml"

            }
               steps {
                // Assuming your tests are testNG tests(send email)
                bat "\"${MAVEN_HOME}\\bin\\mvn\" test -Dtestng.file=mail.xml"

            }
        }
    }
}

