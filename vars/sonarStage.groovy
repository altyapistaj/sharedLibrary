//bitmedi
def call (Map sonarParams = [:]){

    def varProject = sonarParams.projectName
    def varProjectRootName = sonarParams.customWorkspace
    def varProjectCombine = sonarParams.jobName

    def propsFileName="${varProjectCombine}.sonar-project.properties"

    def varProjectPath = "/workspace/$varProject"
    def varProjectRootPath = "$varProjectRootName"
    def varProjectRootSQWorkspacePath = "$varProjectRootPath"

    echo "varProject=${varProjectRootName}"
    echo "varProjectRootName=${varProjectRootName}"
    echo "varProjectCombine=${varProjectCombine}"

   //dir("${varProjectRootName}") {}
       def props = """\
sonar.projectKey=${varProjectCombine}
sonar.projectName=${varProjectCombine}


sonar.sourceEncoding=UTF-8
sonar.projectVersion=1.0

sonar.sources=src
sonar.tests=src
sonar.language=js

sonar.javascript.lcov.reportPaths=coverage/lcov.info

sonar.exclusions=node_modules/**,build/**


""".stripIndent()
       writeFile file: propsFileName, text: props

       echo "Created sonar properties at: ${pwd()}/${propsFileName}"

       def scannerHome = tool(
               name: (sonarParams.scannerToolName ?: 'sonar-scanner')
       )

       withSonarQubeEnv('sq1'){
           bat """
        "${scannerHome}\\bin\\sonar-scanner.bat" -Dproject.settings="${propsFileName}"
        """.trim()
       }


}