def call() {
    pipeline {
        agent any

        parameters {
            string(name: 'ENV', defaultValue: '', description: 'Which environment')
            string(name: 'ACTION', defaultValue: '', description: 'Which Action')
        }

        options {
            ansiColor('xterm')
        }


        stages{
            stage('Init'){
                steps{
                    cleanWs()
                    sh 'terraform init -backend-config=env-${ENV}/state.tfvars'
                }
            }

            stage('Apply'){
                steps{
                    //sh 'echo'
                    sh 'terraform ${ACTION} -auto-approve -var-file=env-${ENV}/main.tfvars'
                }
            }
        }

        post {
            always {
                cleanWs()
            }
        }
    }
}
