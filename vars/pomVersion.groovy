def call() {
    def version = bat(
            script: "mvn help:evaluate -Dexpression=project.version -q -DforceStdout",
            returnStdout : true
    ).trim()

    env.APP_VERSION = version
    echo "Maven version =${env.APP_VERSION}"

}