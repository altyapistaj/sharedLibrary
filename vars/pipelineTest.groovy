def call(Map cfg = [:]){
    def defaults = jobVariables(env.JOB_NAME)

    cfg = defaults + cfg

    node(cfg.agent ?: 'any') {
        if(params.Checkout){
            stage('Checkout') {
                    gitCheckout(
                            gitPathName: vars.gitPathName,
                            jobName: vars.jobName,
                            customWorkspace: vars.customWorkspace,
                            branch: vars.branch
                    )
            }
        }

        if(params.Build){
            stage('Build') {
                dir(cfg.customWorkspace) {
                    mavenStage(text: 'clean install -U -N', pom: "${cfg.pom}")
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
