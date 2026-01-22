def call(Map jsonParams) {
    def packageJson=""
    packageJson = readJSON file: jsonParams.customWorkspace+'/app/package.json'
    version = packageJson.version
    echo "version="+version

}