def call()
{
    properties([
            parameters([
                    booleanParam(name: 'Checkout', defaultValue:true, description: 'Check for Checkout'),
                    booleanParam(name: 'Build', defaultValue:true, description: 'Check for Build'),
                    booleanParam(name: 'Extract', defaultValue:true, description: 'Check for Extract'),
                    booleanParam(name: 'dockerBuild', defaultValue:true, description: 'Check for Docker build'),
                    booleanParam(name: 'pushToZot', defaultValue:true, description: 'Check for push to Zot')
            ])
    ])





}