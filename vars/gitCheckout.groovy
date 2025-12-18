def call() {
    def scmVars = checkout([
            $class: 'GitSCM',
            branches: [[name: env.BRANCH_NAME ]],
            userRemoteConfigs: [[url: "https://github.com/altyapistaj/demo-service.git"]]
    ])
    env.GIT_COMMIT = scmVars.GIT_COMMIT
    env.GIT_BRANCH = scmVars.GIT_BRANCH
    echo "Branch: ${env.GIT_BRANCH}"
    echo "Commit: ${env.GIT_COMMIT}"
}

