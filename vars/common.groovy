def compile() {
    if (app_lang == "nodejs") {
        sh "npm install"
    }
    if (app_lang == "maven") {
        sh "mvn package; mv target/${component}-1.0.jar ${component}.jar "
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
    //sh 'echo ${TAG_NAME} >VERSION'
    //if (app_lang == "maven"){
    //    sh 'zip -r ${component}-${TAG_NAME}.zip ${component}.jar schema VERSION'
    //}else {
    //    sh 'zip -r ${component}-${TAG_NAME}.zip * -x Jenkinsfile'
    //}

    sh 'docker build -t 780395309002.dkr.ecr.us-east-1.amazonaws.com/${component}:${TAG_NAME} .'

}

def artifactUpload() {
    //env.NEXUS_USER = sh ( script: 'aws ssm get-parameter --name prod.nexus.user --with-decryption | jq .Parameter.Value | xargs', returnStdout: true ).trim()
    //env.NEXUS_PASS = sh ( script: 'aws ssm get-parameter --name prod.nexus.pass --with-decryption | jq .Parameter.Value | xargs', returnStdout: true ).trim()
    //wrap([$class: 'MaskPasswordsBuildWrapper',
    //     varPasswordPairs: [[password: NEXUS_PASS],[password: NEXUS_USER]]]) {
    //   sh 'echo ${TAG_NAME} >VERSION'
    //sh 'curl -v -u ${NEXUS_USER}:${NEXUS_PASS} --upload-file ${component}-${TAG_NAME}.zip http://172.31.14.183:8081/repository/${component}/${component}-${TAG_NAME}.zip'

    //}
    sh 'aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 780395309002.dkr.ecr.us-east-1.amazonaws.com'
    sh 'docker push 780395309002.dkr.ecr.us-east-1.amazonaws.com/${component}:${TAG_NAME}'
}

