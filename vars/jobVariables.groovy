def call(String JOB_NAME){
    return [
    slave : 'any',

    maven : 'maven-3.9.12',

    projectName : 'shared-library-test',
    jobName : "demo-service",
    pom : 'pom.xml',

    branch : "library-dependency",
    customWorksapce : "workspace/demo-service",
    gitPathName : "altyapistaj"
    ]

}
