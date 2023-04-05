def call() {
    if (!env.sonar_extra_opts){
        env.sonar_extra_opts=""
    }
    pipeline {
        agent any
        stages {
            stage('Compile/Build') {
                steps {
                    mail bcc: '', body: 'test', cc: '', from: 'mettashalini89@gmail.com', replyTo: '', subject: 'test', to: 'mettashalini89@gmail.com'
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
    }
}