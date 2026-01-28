def call (Map buildParams){
    //TODO: linux/bash komutlarına çevirilecek.
    sh "mvn ${buildParams.text} -f ${buildParams.pom}"
}
