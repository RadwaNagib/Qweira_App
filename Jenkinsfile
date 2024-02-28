pipeline {
    agent any
    stages {
        stage('Stage 1') {
            steps {
                catchError {
                    // Your stage 1 steps
 checkout([$class: 'GitSCM', branches: [[name: 'main']], userRemoteConfigs: [[url: 'https://github.com/RadwaNagib/Qweira_App.git']]])
                }
            }
        }

        stage('Stage 2') {
            steps {
                catchError {
                    // Your stage 2 steps
 bat "\"${MAVEN_HOME}\\bin\\mvn\" clean install"
                }
            }
        }
 stage('Stage 3') {
            steps {
                catchError {
                    // Your stage 3 steps
   archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true 
                }
            }
        }
         post {
        always {
            emailext subject: "Pipeline ${currentBuild.result}: Job ${JOB_NAME} [${BRANCH_NAME}] - Build: Qweira_App",
                body: "Check console output at https://github.com/RadwaNagib/Qweira_App.git",
                to: 'wa.nagib86@gmail.com',
                mimeType: 'text/html',
                attachLog: true,
                attachmentsPattern: '**/target/*.jar'
        }
    }
    }
}


