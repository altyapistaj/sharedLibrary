def call (Map buildParams){
    //TODO: linux/bash komutlarına çevirilecek.
    sh '''           
           $jar = Get-ChildItem -Path "target" -Filter "*.jar" | Select-Object -First 1
           java "-Djarmode=layertools" -jar $jar.FullName extract --destination target
           '''
}

