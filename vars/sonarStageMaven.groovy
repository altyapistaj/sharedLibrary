def call (Map sonarParams = [:]){

    def sonarServerName = sonarParams.sonarServerName  ?: 'sq1'
    def pomPath = sonarParams.pom ?: 'pom.xml'

    bat 'cd'
    bat 'dir'

    withSonarQubeEnv(sonarServerName){
        bat """
            mvn -B -f "${pomPath}" sonar:sonar
"""
    }
}
