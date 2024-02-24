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
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true 
            }
        }
         stage('Run Tests') {
           steps {
        // Assuming your tests are TestNG tests
        bat "\"${MAVEN_HOME}\\bin\\mvn\" test -Dtestng.file=createList_StepDef.java"
    }
        }
         stage('Run Tests1') {
           steps {
        // Assuming your tests are TestNG tests
        bat "\"${MAVEN_HOME}\\bin\\mvn\" test -Dtestng.file=mail.xml"
    }
        }
    }
}

