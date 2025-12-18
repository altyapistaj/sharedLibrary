def call (Map buildParams){
    bat "mvn  -f ${buildParams.pom} ${buildParams.text}"
}
