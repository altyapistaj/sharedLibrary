def call (Map sonarParams = [:]){

    def sonarServerName = sonarParams.sonarServerName  ?: 'sq1'
    def pomPath = sonarParams.pom ?: 'pom.xml'

    bat 'cd'
    bat 'dir'

    withSonarQubeEnv(sonarServerName){
        bat """
            mvn org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.token=squ_1fde7c1ac446e177e3999df97de6c73293d3e0da
"""
    }
}
