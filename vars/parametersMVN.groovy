def call()
{
    properties([
            parameters([
                    booleanParam(name: 'Checkout', defaultValue:true, description: 'Check for Checkout'),
                    booleanParam(name: 'Build', defaultValue:true, description: 'Check for Build'),
            ])
    ])





}
