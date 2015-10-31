(function(){
    "use strict";

    angular
    .module('web')
    .directive("answer",function(){

        return {
            restrict:"AE",
            templateUrl:"/app/main/directives/answer/answer.view.html",
            scope:{
                content:"="
            },
            controller:function($scope){
//                console.log("in answer directive")
            },
            controllerAs:"vm"
        }
    });
})()
