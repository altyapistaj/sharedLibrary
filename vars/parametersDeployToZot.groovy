def call()
{
    properties([
            parameters([
                    booleanParam(name: 'CleanWorkspace', defaultValue:false, description: ''),
                    booleanParam(name: 'Checkout', defaultValue:true, description: ''),
                    booleanParam(name: 'Build', defaultValue:true, description: '' ),
                    booleanParam(name: 'DeployToNexus', defaultValue:true, description: '' ),
                    extendedChoice(name: "JdkVersion",
                            description:"Java Version Selection",
                            type: 'PT_SINGLE_SELECT',
                            value: 'openjdk11,jdk-17,JDK8',
                            defaultValue:'openjdk11' )
            ])
    ])




    stage('Echo Parameters') {
        echo "CleanWorkspace=" + params.Clean_Workspace
        echo "Checkout=" + params.Checkout
        echo "Build=" + params.Build
        echo "DeployToNexus=" + params.DeployToNexus
        echo "Java Version=" + params.JdkVersion
    }
}
