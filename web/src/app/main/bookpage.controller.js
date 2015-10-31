(function() {
    'use strict';

    angular
        .module('web')
        .controller('BookpageController', BookpageController);

    /** @ngInject */
    function BookpageController(questionService) {
        var self = this;
        var currentIndex = 0;
        self.questionContainer = {
            currentQ : {}
        }
        self.setCurrentQ = function(index){
            if(self.questions.length > index){
                self.questionContainer.currentQ = self.questions[index];
            }
        };
        questionService.get().success(function(questions){
            self.questions = questions;
            self.setCurrentQ(0);
        });

        self.hasAnswers = function(){
            return self.questionContainer.currentQ.answers && self.questionContainer.currentQ.answers.length > 0 ;
        }

        self.getAnswers = function(){
            return self.questionContainer.currentQ.answers;
        }
  }


})();
