def call(Map jsonParams) {
    def packageJson = readJSON file: 'package.json'
    env.APP_VERSION = packageJson.version
    echo "package version =${env.APP_VERSION}"

}