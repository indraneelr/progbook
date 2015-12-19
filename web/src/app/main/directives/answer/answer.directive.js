(function(){
    "use strict";

    angular
    .module('web')
    .directive("answer",function($state){

        return {
            restrict:"AE",
            templateUrl:"/app/main/directives/answer/answer.view.html",
            scope:{
                content:"=",
                questionId:"="
            },
            link:function($scope,elm,attr){
                $scope.contentAsHtml =""
                try{
                    var parsedJson= JSON.parse($scope.content.content)||{};
                    $scope.contentAsHtml = parsedJson.asHtml
                }
                catch(ex){
                    $scope.contentAsHtml =  $scope.content.content
                }
            },
            controllerAs:"vm"
        }
    });
})()
