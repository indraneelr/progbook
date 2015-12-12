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
            controller:function($scope){
                var self = this;
                self.saveQuestion = function(question){
                    modalService.openSaveQuestionModal(question);
                }
            },
            controllerAs:"vm"
        }
    };
})()
