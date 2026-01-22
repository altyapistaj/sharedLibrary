def call() {
    def pom = readMavenPom file: 'pom.xml'
    env.APP_VERSION = pom.version

    echo "Maven version =${env.APP_VERSION}"

}