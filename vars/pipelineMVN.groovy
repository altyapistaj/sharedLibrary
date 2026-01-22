def call(Map cfg = [:]){
    def defaults = jobVariables(env.JOB_NAME)

    cfg = defaults + cfg

    node() {
        if(params.Checkout){
            stage('Checkout') {
                gitCheckout(
                        //gitPathName: cfg.gitPathName,
                        gitAdressAndName: cfg.gitAdressAndName,
                        customWorkspace: cfg.customWorkspace,
                        branch: cfg.branch
                )
                echo "${cfg.customWorkspace}"
                echo "${cfg.jobName}"
                echo "${cfg.gitPathName}"
            }
        }

        if(params.Build){
            stage('Build') {
                dir(cfg.customWorkspace) {
                    mavenStage(text: 'clean install -U -N', pom: cfg.pom)
                }
            }
        }
    }
}
