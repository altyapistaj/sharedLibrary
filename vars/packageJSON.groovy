def call(Map jsonParams) {
    def packageJson = readJSON file: 'package.json'
    def version = packageJson.version
    echo "version="+version
    return version

}