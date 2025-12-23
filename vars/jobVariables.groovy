def call(String JOB_NAME){
    return [
            projectName : 'shared-library-test',
            jobName : "",
            pom : 'pom.xml',

            branch : "library-dependency",
            customWorkspace : "workspace/" + JOB_NAME + "/",
            gitPathName : "altyapistaj",
            gitAdressAndName : "demo-service"
    ]

}

