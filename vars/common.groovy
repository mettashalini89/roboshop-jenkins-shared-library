def compile() {
    if (app_lang == "nodejs") {
        sh "npm install"
    }
    if (app_lang == "maven") {
        sh "mvn package"
    }
}

def testcases() {
    // mvn test for maven
    // go test for goland
    // python -m unittests for python
    // npm test for nodejs

    sh "echo test"
}

def qualitycheck() {
    withAWSParameterStore(credentialsId: '', naming: 'absolute', path: '/sonarqube', recursive: true, regionName: 'us-east-1') {
        sh 'sonar-scanner -Dsonar.host.url=http://172.31.0.236:9000 -Dsonar.login=admin -Dsonar.password=admin123 -Dsonar.projectKey=${component} ${sonar_extra_opts}'
    }

}

