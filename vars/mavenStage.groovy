def call (Map buildParams){
    //TODO: linux/bash komutlarına çevirilecek.
    bat "mvn ${buildParams.text} -f ${buildParams.pom}"
}
