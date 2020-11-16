var app = angular.module('aitu-project', []);
app.controller('registerCtrl', function($scope, $http) {

    $scope.register = function(user){
    alert();
        console.log(user);
        $http({
                    url: 'http://127.0.0.1:8080/registration',
                    method: "POST",
                    headers: {
                        "Access-Control-Allow-Origin": "*",
                        "Content-Type": "application/json",
                    },
                    data: user,
                })
                    .then(function (response) {
                            console.log(response.data)

                        }).catch((response)=>{
                            console.log(response)

                        });
     }
});