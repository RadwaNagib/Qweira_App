pipeline {
    agent any
    environment {
                        PROJECT_ROOT = 'C:\\Users\\Shass\\IdeaProjects\\Qweira_App'
                        WORKSPACE_WINDOWS = 'C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\test_currentBuild'
                        WORKSPACE = 'C:/ProgramData/Jenkins/.jenkins/workspace/test_currentBuild'
                        TARGET_FOLDER = 'target'
                        SUREFIRE_REPORTS = '/surefire-reports'
                        HTML_REPORT = '/emailable-report.html'
                        EMAIL_RECIPIENT = 'wa.nagib86@gmail.com'

                    }
    stages {
        stage('Stage 1') {
            steps {
                catchError {
                    // Your stage 1 steps
 checkout([$class: 'GitSCM', branches: [[name: 'main']], userRemoteConfigs: [[url: 'https://github.com/RadwaNagib/Qweira_App.git']]])
                }
            }
        }

                stage('Cleanup') {
                            steps {
                                script {
                                    echo "Starting 'Cleanup' Stage!!"
                                    def targetPath = "${WORKSPACE_WINDOWS}\\${TARGET_FOLDER}"
                                    if (fileExists(targetPath)) {
                                        // Delete the target folder
                                        bat "rmdir /s /q ${targetPath}"
                                        echo "Target directory removed successfully : ${targetPath}"
                                    } else {
                                        echo "Target directory does not exist at : ${targetPath}. No cleanup needed."
                                    }
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

                     script {
                         echo "Starting 'Mail Distribution' Stage!!"
                         //bat "C:/Users/Agami/scoop/apps/allure/2.25.0/bin/allure.bat generate --single-file allure-results --clean"

                         def testNGAttachment = "${TARGET_FOLDER}${SUREFIRE_REPORTS}${HTML_REPORT}"
                         def testNGReportContent = readFile(file: testNGAttachment)

                         if (fileExists(allureAttachment) || fileExists(testNGAttachment)) {
                             emailext(
                                     subject: "Allure Results",
                                     body: "Please find the attached test results. \n\n${testNGReportContent}",
                                     to: "${EMAIL_RECIPIENT}",
                                     mimeType: 'text/html',
                                     attachmentsPattern: "${testNGAttachment}"
                             )
                         } else {
                             echo "File doesn't exist at: ${testNGAttachment}"
                         }
                     }
                }
            }
        }




