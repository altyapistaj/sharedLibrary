def call (Map sonarParams = [:]){

    def sonarServerName = sonarParams.sonarServerName  ?: 'sq1' //jenkins system ayarlarında tanımlanan SonarQube adı
    def pomPath = sonarParams.pom ?: 'pom.xml'

    withSonarQubeEnv(sonarServerName){
        sh """
            mvn org.sonarsource.scanner.maven:sonar-maven-plugin:sonar
"""
    }
}
