def call() {
    pipeline {
        agent any

        options {
            ansiColor('xterm')
        }

        parameters {
            string(name: 'app_version', defaultValue: '', description: 'App version')
            string(name: 'component', defaultValue: '', description: 'Component')
            string(name: 'environment', defaultValue: '', description: 'Environment')
        }

        stages {
            stage('Clone Application') {
                steps {
                    dir('App'){
                        git branch: 'main', url: "https://github.com/mettashalini89/${component}"
                    }
                }
            }
            stage("Deploy Helm Chart") {
                steps {
                    script {
                        sh 'helm upgrade -i ${component} . -f /App/helm/${environment}.yaml --set appversion=${app_version}'
                        }
                    }

                }
            }
        }
        post {
            always {
                cleanWs()
            }
        }
}