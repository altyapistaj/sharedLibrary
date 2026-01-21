def call()
{
    properties([
            parameters([
                    booleanParam(name: 'Checkout', defaultValue:true, description: 'Check for Checkout'),
                    booleanParam(name: 'Install', defaultValue:true, description: 'Check for Install'),
                    booleanParam(name: 'Test', defaultValue:true, description: 'Check for Test'),
                    booleanParam(name: 'SonarQube', defaultValue:true, description: 'Check for SonarQube'),
                    booleanParam(name: 'Build', defaultValue:true, description: 'Check for Build'),
                    booleanParam(name: 'dockerBuild', defaultValue:true, description: 'Check for Docker build'),
                    booleanParam(name: 'pushToZot', defaultValue:true, description: 'Check for push to Zot')
            ])
    ])





}
