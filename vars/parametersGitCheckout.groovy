def call()
{
    properties([
            parameters([
                    booleanParam(name: 'Checkout', defaultValue:true, description: ''),
                    choice(name: "JdkVersion",
                            description:"Java Version Selection",
                            type: 'PT_SINGLE_SELECT',
                            value: 'openjdk11,jdk-17,JDK8',
                            defaultValue:'openjdk11' )
            ])
    ])





}