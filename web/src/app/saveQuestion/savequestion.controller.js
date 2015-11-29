(function() {
    'use strict';

    angular
        .module('web')
          .controller('SaveQuestionController', SaveQuestionController);

    /** @ngInject */
    function SaveQuestionController(questionService,$state) {
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
        if($state.params.questionId){
            questionService.getById($state.params.questionId).success(function(question){
                angular.copy(question,self.question);
            });
        }
    }
})();
