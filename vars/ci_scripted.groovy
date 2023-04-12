def call() {
    if (!env.sonar_extra_opts){
        env.sonar_extra_opts=""
    }

    if (env.TAG_NAME !=~ ".*"){
        env.GTAG = "true"
    }
    node('workstation') {

        try {
            stage('Check out Code') {
                sh 'ls -l'
                cleanWs()
                sh 'ls -l'
                git branch: 'main', url: 'https://github.com/mettashalini89/cart'
                sh 'ls -l'
            }

            sh 'env'

            if (env.BRANCH_NAME!= "main"){
                stage('Compile/Build') {
                    common.compile()
                }
            }

            if (env.GTAG != "true"){
                stage('Test Cases') {
                    common.testcases()
                }

            }

            stage('Quality Check') {
                common.qualitycheck()
            }
        } catch (e) {
            mail  body: "<h1>${component}-Pipeline Failure \n ${BUILD_URL}</h1>", from: "mettashalini89@gmail.com", subject: "${component}-Pipeline Failure", to: "mettashalini89@gmail.com",  mimeType: "text/html"
        }
    }
}

