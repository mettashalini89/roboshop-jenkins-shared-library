def call() {
    pipeline {
        agent any

        parameters {
            string(name: 'ENV', defaultValue: '', description: 'Which environment')
        }

        options {
            ansiColor('xterm')
        }


        stages{
            stage('Init'){
                steps{
                    sh 'terraform init -backend-config=env-${ENV}/state.tfvars'
                }
            }

            stage('Apply'){
                steps{
                    sh 'echo'
                    //sh 'terraform apply -auto-approve -var-file=env-dev/main.tfvars'
                }
            }
        }
    }
}
