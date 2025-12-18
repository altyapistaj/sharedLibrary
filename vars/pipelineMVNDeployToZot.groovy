def call () {

    jobVariables("${JOB_NAME}")

    pipeline {

        agent any

        options {
            disableConcurrentBuilds()
        }

        tools {
            jdk jobVariables.jdk
            maven jobVariables.maven
        }

        stages {
            stage('MavenSnapshotCheck') {
                steps {
                    script{
                        echo env.BRANCH_NAME
                        if (env.BRANCH_NAME.startsWith("master")) {
                            mavenSnapshotCheck check: 'true'
                            echo "MavenSnapshotCheck is done!!!"
                        }
                    }
                }
            }

            stage('Clean Workspace') {
                when { expression { params.Clean_Workspace }}
                steps {
                    sh "rm -rf ${JOB_NAME}"
                }
            }

            stage('Checkout') {
                when { expression { params.Checkout }}
                steps{
                    gitCheckout(gitAddressAndName: jobVariables.gitAddressAndName)
                }
            }

            stage('Build') {
                when { expression { params.Build } }
                steps{
                    sh "java -version"
                    sh "mvn -version"
                    mavenStage(text: 'clean install -U -N ' , pom: jobVariables.pom)
                }
            }

            stage('SonarQube'){
                when { expression { jobVariables.sonar }}
                steps{
                    sonarStage(projectName: jobVariables.projectName, jobPathName: jobVariables.jobPathName, jobName: jobVariables.jobName ,customWorkspace: "${WORKSPACE}"  )
                }
            }

            stage("Quality Gate") {
                when { expression { jobVariables.qg }}
                steps {
                    timeout(time: 1, unit: 'HOURS') {
                        waitForQualityGate true
                    }
                }
            }

            stage('DeployToNexus') {
                when { expression { params.DeployToNexus }}
                steps{
                    mavenStage(text: ' deploy ' , pom: jobVariables.pom)
                }
            }
        }

        post{
            failure {
                notifications()
            }
            success {
                fingerprint '**/target/*.jar'
            }
        }

    }
}