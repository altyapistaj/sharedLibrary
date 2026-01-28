def call (Map buildParams){
    //TODO: linux/bash komutlarına çevirilecek.
    sh '''           
           jar=$(ls target/*.jar | head -n 1)
           java -Djarmode=layertools -jar "$jar" extract --destination target
           '''
}

