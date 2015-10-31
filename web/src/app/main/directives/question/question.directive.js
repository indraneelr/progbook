(function(){
    "use strict";

    angular
    .module('web')
    .directive("question",function(){

        return {
            restrict:"AE",
            templateUrl:"/app/main/directives/question/question.view.html",
            scope:{
                content:"="
            },
            controller:function($scope){
//                console.log("in question directive")
            },
            controllerAs:"vm"
        }
    });
})()
