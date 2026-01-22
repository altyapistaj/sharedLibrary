def call (){
    //TODO: linux/bash komutlarına çevirilecek.
    bat "docker build -f Dockerfile . --tag localhost:5000/${env.JOB_NAME.tokenize('/')[1]}:%APP_VERSION%"
}

