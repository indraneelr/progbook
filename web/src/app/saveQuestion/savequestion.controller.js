(function() {
    'use strict';

    angular
        .module('web')
          .controller('SaveQuestionController', SaveQuestionController);

    /** @ngInject */
    function SaveQuestionController(questionService,$uibModalInstance,questionToSave) {
        var self = this;
        var setCreator = function(question){
            question.creator= {
                id:1
            }
        }
        self.question = {}
        setCreator(self.question);
        self.categories =[];
        self.submit = function(){
            questionService.save(self.question);
            $uibModalInstance.close(self.question)
        }
        questionService.getCategories().success(function(categories){
            angular.copy(categories,self.categories);
        });
        if(!_.isEmpty(questionToSave)){
            questionService.getById(questionToSave.id).success(function(question){
                angular.copy(question,self.question);
            });
        }
    }
})();
