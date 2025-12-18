def call (Map buildParams){
    //TODO: linux/bash komutlarına çevirilecek.
    powershell '''           
           $jar = Get-ChildItem -Path "target" -Filter "*.jar" | Select-Object -First 1
           java "-Djarmode=layertools" -jar $jar.FullName extract --destination target
           '''
}

