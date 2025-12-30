def call (Map sonarParams = [:]){

    def sonarServerName = sonarParams.sonarServerName  ?: 'SonarQube-25.12'
    def pomPath = sonarParams.pom ?: 'pom.xml'


    withSonarQubeEnv(sonarServerName){
        bat """
            mvn -B -f "${pomPath}" sonar:sonar
""".trim()
    }
}
