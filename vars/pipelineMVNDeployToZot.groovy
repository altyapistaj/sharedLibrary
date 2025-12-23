def call () {
    pipeline {
        stage('Init') {
            steps {
                def vars = jobVariables(env.JOB_NAME)
            }
        }
            stage('Checkout') {
                when { expression { params.Checkout } }
                steps {
                    gitCheckout(
                            gitPathName: vars.gitPathName,
                            jobName: vars.jobName,
                            customWorkspace: vars.customWorkspace,
                            branch: vars.branch
                    )
                }
            }
            stage('build') {
                when { expression { params.Build } }
                steps {
                    dir(vars.customWorkspace) {
                        mavenStage(text: 'clean install -U -N', pom: '${vars.pom}')
                    }
                }
            }
            stage('extract') {
                when { expression { params.Extract } }
                steps {
                    dir(vars.customWorkspace) {
                        extractJar()
                    }
                }
            }
            stage('build Docker') {
                when { expression { params.dockerBuild } }
                steps {
                    dir(vars.customWorkspace) {
                        dockerBuild()
                    }
                }
            }
            stage('push to Zot') {
                when { expression { params.pushToZot } }
                steps {
                    pushToZot()
                }
            }


        }
    }