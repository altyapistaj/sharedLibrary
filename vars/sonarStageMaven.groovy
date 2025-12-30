def call (Map sonarParams = [:]){

    def sonarServerName = sonarParams.sonarServerName  ?: 'sq1'
    def pomPath = sonarParams.pom ?: 'pom.xml'

    bat 'cd'
    bat 'dir'

    withSonarQubeEnv(sonarServerName){
        bat """
            mvn -B -f "${pomPath}" clean package
            mvn -B -f "${pomPath}" org.sonarsource.scanner.maven:sonar-maven-plugin:3.10.0.2594:sonar
""".trim()
    }
}
