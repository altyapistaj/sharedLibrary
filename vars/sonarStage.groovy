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

   ws("${varProjectRootName}") {
       def props = """\
sonar.projectKey=${varProjectCombine}
sonar.projectName=${varProjectCombine}
sonar.projectVersion=${env.BUILD_NUMBER ?: "1"}


sonar.sourceEncoding=UTF-8

sonar.sources=src/main/java
sonar.tests=src/test/java

sonar.java.binaries=target/classes

sonar.junit.reportPaths=target/surefire-reports
sonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
""".stripIndent()
       writeFile file: propsFileName, text: props

       echo "Created sonar properties at: ${pwd()}/${propsFileName}"

       def scannerHome = tool(
               name: (sonarParams.scannerToolName ?: 'sonar-scanner-25.12.0.117093'),
               type: 'hudson.plugins.sonar.sonarRunnerInstallation'
       )

       withSonerQubeEnv('sq1')

       bat """
        "${scannerHome}\\bin\\sonar-scanner.bat -Dproject.settings="${propsFileName}""
""".trim()
   }
}