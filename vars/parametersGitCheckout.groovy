def call()
{
    properties([
            parameters([
                    booleanParam(name: 'Checkout', defaultValue:true, description: 'Check for Checkout')
            ])
    ])





}