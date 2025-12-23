def call () {

    jobVariables("${JOB_NAME}")

    pipeline {

            tools {
                maven jobVariables.maven
                echo 'maven'
            }

            stage('Checkout') {
                when { expression { params.Checkout } }
                steps {
                    gitCheckout(
                            gitPathName: jobVariables.gitPathName,
                            jobName: jobVariables.jobName,
                            customWorkspace: jobVariables.customWorkspace,
                            branch: jobVariables.branch
                    )
                }
            }
            stage('build') {
                when { expression { params.Build } }
                steps {
                    dir(jobVariables.customWorkspace) {
                        mavenStage(text: 'clean install -U -N', pom: '${jobVariables.pom}')
                    }
                }
            }
            stage('extract') {
                when { expression { params.Extract } }
                steps {
                    dir(jobVariables.customWorkspace) {
                        extractJar()
                    }
                }
            }
            stage('build Docker') {
                when { expression { params.dockerBuild } }
                steps {
                    dir('workspace/demo-service') {
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