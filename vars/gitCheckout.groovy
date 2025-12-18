def call(Map stageParams) {
    def scmVars = checkout([
            $class: 'GitSCM',
            branches: [[name: env.BRANCH_NAME ]],
            userRemoteConfigs: [[url: "https://github.com/"  + stageParams.gitPathName + "/" + stageParams.jobName + ".git"]],
            extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: stageParams.customWorkspace]]
    ])
    env.GIT_COMMIT = scmVars.GIT_COMMIT
    env.GIT_BRANCH = scmVars.GIT_BRANCH
    echo "Branch: ${env.GIT_BRANCH}"
    echo "Commit: ${env.GIT_COMMIT}"
}

