def call() {
    if (!env.sonar_extra_opts){
        env.sonar_extra_opts=""
    }
    pipeline {
        agent any
        stages {
            stage('Compile/Build') {
                steps {
                    sh 'env'
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
                mail  body: '${component}-Pipeline Failure \n ${BUILD_URL}', from: 'mettashalini89@gmail.com', subject: '${component}-Pipeline Failure', to: 'mettashalini89@gmail.com'
            }
        }
    }
}