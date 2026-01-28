def call (Map buildParams){
    sh "mvn ${buildParams.text} -f ${buildParams.pom}"
}
