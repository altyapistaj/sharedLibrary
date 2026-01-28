def call (){
    sh 'npm test -- --coverage --testMatch="**/src/**/*.test.js"'
}
