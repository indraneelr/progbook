(function() {
    'use strict';

    angular
        .module('web')
        .controller('BookpageController', BookpageController);

    /** @ngInject */
    function BookpageController(questionService,$stateParams,$rootScope,$state) {
        var self = this;
        var currentIndex = 0;
        self.questions = [];
        self.questionsByCategories = [];
        self.questionContainer = {
            currentQ : {}
        }
        self.languages = [];

        self.setCurrentQ = function(questionId){
            if(self.questions.length > 0){
                if(!questionId){
                    self.questionContainer.currentQ = self.questions[0];
                    return;
                }
                var selectedQuestion = _.find(self.questions,function(question){
                    return question.id.toString() === questionId;
                })
                self.questionContainer.currentQ = selectedQuestion || self.questions[0];
            }
        };
        questionService.get().success(function(questions){
            console.log($state);
            self.questions = angular.copy(questions,self.questions);
            self.questionsByCategories = _.groupBy(self.questions,"category")
            self.setCurrentQ($state.params.questionId);
        });

        $rootScope.$on('$stateChangeSuccess',function(event){
            console.log($state);
            self.setCurrentQ($state.params.questionId);
        });

        self.hasAnswers = function(){
            return self.questionContainer.currentQ.answers && self.questionContainer.currentQ.answers.length > 0 ;
        }

        self.getAnswers = function(){
            return self.questionContainer.currentQ.answers;
        }
  }


})();
