def call () {

    pipeline {

        agent any

        tools{
            maven 'maven-3.9.12'
        }

        stage('Checkout'){
            when { expression { params.Checkout }}
            steps {
                gitCheckout(
                        gitPathName: 'altyapistaj',
                        jobName: 'demo-service',
                        customWorkspace: 'workspace/demo-service',
                        branch: 'library-dependency'
                )
            }
        }
        stage('build'){
            when { expression { params.Build }}
            steps {
                dir('workspace/demo-service'){
                    mavenStage(text: 'clean install -U -N' , pom: 'pom.xml')
                }
            }
        }
        stage('extract'){
            when { expression { params.Extract }}
            steps {
                dir('workspace/demo-service'){
                    extractJar()
                }
            }
        }
        stage('build Docker'){
            when { expression { params.dockerBuild }}
            steps {
                dir('workspace/demo-service'){
                    dockerBuild()
                }
            }
        }
        stage('push to Zot'){
            when { expression { params.pushToZot }}
            steps {
                pushToZot()
            }
        }




    }
}