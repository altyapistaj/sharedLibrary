def call (Map buildParams){
    bat "mvn ${buildParams.text} -f ${buildParams.pom}"
}
