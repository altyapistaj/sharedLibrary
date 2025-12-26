def call (){
    //TODO: linux/bash komutlarına çevirilecek.
    bat "docker push localhost:5000/${env.JOB_NAME.tokenize('/')[1]}:latest"
}
