def call(Map cfg = [:]){
    def defaults = jobVariables(env.JOB_NAME)

    cfg = defaults + cfg

    node() {
        if(params.Checkout){
            stage('Checkout') {
                    gitCheckout(
                            gitAdressAndName: cfg.gitAdressAndName,
                            customWorkspace: cfg.customWorkspace,
                            branch: cfg.branch,
                            organizationName : cfg.organizationName
                    )
                echo "${cfg.customWorkspace}"
                echo "${cfg.jobName}"
                echo "${cfg.gitPathName}"
            }
        }

        stage('Version'){
            pomVersion()
        }

        if(params.Build){
            stage('Build')
                    mavenStage(text: 'clean install -U -N', pom: cfg.pom)


        }
        if(params.SonarQube){
            stage('SonarQube') {
                    sonarStageMaven()
            }
        }

        if(params.Extract){
            stage('extract') {
                extractJar()
            }
        }

        if(params.dockerBuild){
            stage('build Docker') {
                    dockerBuild()

            }
        }
        if(params.pushToZot){
            stage('push to Zot') {
                pushToZot()
            }
        }

    }
}
