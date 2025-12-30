def call (Map sonarParams){

    def varProject = sonarParams.projectName
    def varProjectRootName = sonarParams.customWorkspace
    def varProjectCombine = sonarParams.jobName

    def varProjectPath = "/var/lib/jenkins/workspace/$varProject"
    def varProjectRootPath = "$varProjectRootName"
    def varProjectRootSQWorkspacePath = "$varProjectRootPath"

    echo "varProjectRootName=$varProjectRootName"
    echo "varProjectRootPath=$varProjectRootPath"
    echo "varProjectRootSQWorkspacePath=$varProjectRootSQWorkspacePath"

    //TODO: sh komutlarına çevirilecek
    bat '''
        sonarprojectfile='''+varProjectPath+'''/'''+sonarParams.jobName+'''.sonar-project.properties
        echo "sonar.projectKey='''+varProjectCombine+'''" > $sonarprojectfile
        echo "sonar.projectName='''+varProjectCombine+'''" >> $sonarprojectfile
        echo "sonar.projectVersion=$BUILD_NUMBER" >> $sonarprojectfile
        echo "sonar.sourceEncoding=UTF-8" >> $sonarprojectfile 
        echo "sonar.sources='''+varProjectRootPath+'''" >> $sonarprojectfile
        echo "sonar.projectBaseDir='''+varProjectRootSQWorkspacePath+'''" >> $sonarprojectfile
        echo "sonar.java.binaries='''+varProjectRootPath+'''" >> $sonarprojectfile
        echo "sonar.java.coveragePlugin=jacoco" >> $sonarprojectfile
            
             /var/lib/jenkins/workspace/unit.sh $sonarprojectfile $varProjectPath
        
        '''
    //TODO: sh komutlarına çevirilecek
    ws("$varProjectRootSQWorkspacePath"){
        def scannerHome = tool name: 'sonar-scanner-5.4.0.6343', type:'hudson.plugins.sonar.SonarRunnerInstallation';
        withSonarQubeEnv('SonarQube-25.12'){
            bat " ${scannerHome}/bin/sonar-scanner -Dproject.settings=${varProjectPath}/"+sonarParams.jobName+".sonar-project.properties"
        }
    }
}