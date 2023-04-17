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
    withAWSParameterStore(credentialsId: 'PARAM1', naming: 'absolute', path: '/sonarqube', recursive: true, regionName: 'us-east-1') {
       // sh 'sonar-scanner -Dsonar.host.url=http://172.31.0.236:9000 -Dsonar.login=${SONARQUBE_USER} -Dsonar.password=${SONARQUBE_PASS} -Dsonar.projectKey=${component} ${sonar_extra_opts}'
       sh 'echo ok'
    }
}

def prepareArtifacts() {
    sh 'echo ${TAG_NAME} >VERSION'
/*    if (app_lang == "nodejs" || app_lang == "angular"){
        sh 'zip -r ${component}-${TAG_NAME}.zip * -x Jenkinsfile'
    }*/

}

def artifactUpload() {
    sh 'echo ${TAG_NAME} >VERSION'
/*    if (app_lang == "nodejs" || app_lang == "angular"){
        sh 'curl -v -u admin:admin123 --upload-file ${component}-${TAG_NAME}.zip http://172.31.14.183:8081/repository/${component}/${component}-${TAG_NAME}.zip'
    }*/
}

