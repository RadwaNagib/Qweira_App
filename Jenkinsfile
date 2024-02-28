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
      
    }

   post {
    always {
        emailext subject: "Pipeline ${Qweira_App.result}: Job ${JOB_NAME} [${BUILD_NUMBER}]",
                  body: "Check console output at ${BUILD_URL}",
                  recipientProviders: [[$class: 'DevelopersRecipientProvider']],
                  to: 'wa.nagib86@gmail.com',  // Add your email address here
                  mimeType: 'text/html',      // You can adjust the mimeType as needed
                  attachLog: true,             // Attach build log to email
                  attachmentsPattern: '**/target/*.jar',   Add your specific artifact path/pattern
                //  replyTo: 'your-reply-email@example.com',   Add your reply-to email if needed
                  //cc: 'additional-email@example.com',         Add additional CC email if needed
                //  bcc: 'hidden-email@example.com'              Add hidden BCC email if needed
    
}
}
}

