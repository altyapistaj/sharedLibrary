def call(Map cfg = [:]){
    def defaults = jobVariables(env.JOB_NAME)

    cfg = defaults + cfg

    node() {
        if(params.Checkout){
            stage('Checkout') {
                gitCheckout(
                        gitAdressAndName: cfg.gitAdressAndName,
                        customWorkspace: cfg.customWorkspace,
                        branch: cfg.branch
                )
                echo "${cfg.customWorkspace}"
                echo "${cfg.jobName}"
            }
        }

        if(params.Install){
            stage('Install') {
                    bat 'npm ci'

            }
        }

        if(params.Test){
            stage('Test') {
                bat 'npm test -- --coverage --testMatch="**/src/**/*.test.js"'
            }
        }

        if(params.SonarQube){
            stage('SonarQube') {
                sonarStage(
                        projectName : cfg.projectName,
                        jobName: cfg.jobName
                )
            }
        }

        if(params.Build){
            stage('Build') {
                    bat 'npm run build'

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
