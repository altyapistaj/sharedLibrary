def call()
{
    properties([
            parameters([
                    booleanParam(name: 'Checkout', defaultValue:true, description: ''),
                    extendedChoice(name: "JdkVersion",
                            description:"Java Version Selection",
                            type: 'PT_SINGLE_SELECT',
                            value: 'openjdk11,jdk-17,JDK8',
                            defaultValue:'openjdk11' )
            ])
    ])




    stage('Echo Parameters') {
        echo "Checkout=" + params.Checkout
    }
}