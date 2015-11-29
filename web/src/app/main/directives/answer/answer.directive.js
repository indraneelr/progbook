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
            controller:function($scope){

            },
            controllerAs:"vm"
        }
    });
})()
