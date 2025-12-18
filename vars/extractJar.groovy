def call (Map buildParams){
    bat '''
           rm -rf docker/layers
           mkdir -p docker/layers
           JAR_NAME=$(ls target/*.jar |head -n 1)
           java -Djarmode=layertools -jar $JAR_NAME extract --destination docker/layers
           '''
}

