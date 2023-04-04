def call() {
    pipeline {
        agent any
        stages {
            stage('Compile/Build') {
                steps {
                    script {
                        if (env_lang == "nodejs"){
                            sh "npm install"
                        }
                        if (env_lang == "maven"){
                            sh "mvn package"
                        }
                    }
                }
            }

            stage('Test Cases') {
                steps {
                    echo 'Test Cases'
                }
            }
        }
    }
}