(function(){
    "use strict";

    angular
    .module('web')
    .directive("question",question);

    /** @ngInject */
    function   question(modalService){
        return {
            restrict:"AE",
            templateUrl:"/app/main/directives/question/question.view.html",
            scope:{
                content:"="
            },

            link:function($scope){
                $scope.contentAsHtml =""
                var updateContentAsHtml = function(){
                    try{
                        var parsedJson= JSON.parse($scope.content.description)||{};
                        $scope.contentAsHtml = parsedJson.asHtml;
                    }
                    catch(ex){
                        $scope.contentAsHtml =  $scope.content.description;
                    }
                }
                $scope.$watch("content.description",function(newVal,oldVal){
                    if(newVal !== oldVal){
                        updateContentAsHtml();
                    }
                });
            },
            controllerAs:"vm"
        }
    };
})()
