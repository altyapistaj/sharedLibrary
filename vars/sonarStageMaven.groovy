def call (Map sonarParams = [:]){

    def sonarServerName = sonarParams.sonarServerName  ?: 'sq1'
    def pomPath = sonarParams.pom ?: 'pom.xml'

    withSonarQubeEnv(sonarServerName){
        bat """
            mvn org.sonarsource.scanner.maven:sonar-maven-plugin:sonar
"""
    }
}
