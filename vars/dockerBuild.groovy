def call (){
    //TODO: linux/bash komutlarına çevirilecek.
    sh "docker build -f Dockerfile . --tag localhost:5000/${env.JOB_NAME.tokenize('/')[1]}:${env.APP_VERSION}"
}

