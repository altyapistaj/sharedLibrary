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

        if(params.install){
            stage('Install') {
                dir(cfg.customWorkspace) {
                    bat 'npm ci'
                }
            }
        }


        if(params.Test){
            stage('Test') {
                bat 'npm test -- --coverage'
            }
        }

        if(params.Build){
            stage('Build') {
                dir(cfg.customWorkspace) {
                    bat 'npm run build'
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
