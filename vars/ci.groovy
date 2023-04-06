def call() {
    if (!env.sonar_extra_opts){
        env.sonar_extra_opts=""
    }
    pipeline {
        agent any
        stages {
            stage('Compile/Build') {
                steps {
                    script {
                       common.compile()
                    }
                }
            }

            stage('Test Cases') {
                steps {
                    script {
                        common.testcases()
                    }
                }
            }

            stage('Quality Check') {
                steps {
                    script {
                        common.qualitycheck()
                    }
                }
            }
        }
        post {
            failure {
                mail  body: "<h1>${component}-Pipeline Failure \n ${BUILD_URL}</h1>", from: "mettashalini89@gmail.com", subject: "${component}-Pipeline Failure", to: "mettashalini89@gmail.com",  mimeType: "text/html"
            }
        }
    }
}