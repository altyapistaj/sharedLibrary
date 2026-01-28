def call (){
    sh "docker push localhost:5000/${env.JOB_NAME.tokenize('/')[1]}:${env.APP_VERSION}"
}
