def call(Map stageParams) {
    def branch = stageParams.branch ?: (env.BRANCH_NAME ?: 'main')


    def scmVars = checkout([
            $class: 'GitSCM',
            branches: [[name: "*/${branch}" ]],
            userRemoteConfigs: [[credentialsId: '85c6a4a3-732d-4212-b0fe-97575eec430a', url: "https://github.com/" + stageParams.organizationName + "/" + stageParams.gitAdressAndName + ".git"]],
            extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: stageParams.customWorkspace]]
    ])

    env.GIT_COMMIT = scmVars.GIT_COMMIT
    env.GIT_BRANCH = scmVars.GIT_BRANCH
    echo "Branch: ${env.GIT_BRANCH}"
    echo "Commit: ${env.GIT_COMMIT}"
}

