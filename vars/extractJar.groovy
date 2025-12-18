def call (Map buildParams){
    //TODO: linux/bash komutlarına çevirilecek.
    powershell '''
           if (Test-Path '${layersDir}) { Remove-Item -Recurse -Force '${layersDir}' }
           New-Item -ItemType Directory -Force -Path '${layersDir}' | out-Null
           
           \$jar = Get-ChildItem -Path 'target' -Filter '*.jar' | Select-Object -First 1
           if(-not \$jar) {throw 'target klasorunde .jar yok.}
           
           Write-Host "Jar bulundu \$([\$jar].FullName)"
           java Djarmode0layertools -jar \$jar.FullName extract --destination '${layersDir}'
           '''
}

