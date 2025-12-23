def call(Map cfg = [:]){
    def defaults = jobVariables(env.JOB_NAME)

    cfg = defaults + cfg

    node() {
        if(params.Checkout){
            stage('Checkout') {
                    gitCheckout(
                            gitPathName: cfg.gitPathName,
                            jobName: cfg.jobName,
                            customWorkspace: cfg.customWorkspace,
                            branch: cfg.branch
                    )
                echo "${defaults.customWorkspace}"
            }
        }

        if(params.Build){
            stage('Build') {
                dir(cfg.customWorkspace) {
                    mavenStage(text: 'clean install -U -N', pom: cfg.pom)
                }
            }
        }

        if(params.Extract){
            stage('extract') {
                dir(cfg.customWorkspace) {
                    extractJar()
                }
            }
        }

        if(params.dockerBuild){
            stage('build Docker') {
                dir(cfg.customWorkspace) {
                    dockerBuild()
                }
            }
        }
        if(params.pushToZot){
            stage('push to Zot') {
                pushToZot()
            }
        }

    }
}
