def call(Map jsonParams) {
    def packageJson=""
    packageJson = readJSON file: 'package.json'
    version = packageJson.version
    echo "version="+version
    return version

}