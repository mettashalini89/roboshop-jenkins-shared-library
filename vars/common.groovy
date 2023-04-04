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