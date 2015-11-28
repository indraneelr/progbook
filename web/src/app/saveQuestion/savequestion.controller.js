(function() {
    'use strict';

    angular
        .module('web')
          .controller('SaveQuestionController', SaveQuestionController);

    /** @ngInject */
    function SaveQuestionController(questionService,$stateParams) {
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
        }
        questionService.getCategories().success(function(categories){
            angular.copy(categories,self.categories);
        });
        if($stateParams.questionId){
            questionService.getById($stateParams.questionId).success(function(question){
                angular.copy(question,self.question);
            });
        }
    }
})();
