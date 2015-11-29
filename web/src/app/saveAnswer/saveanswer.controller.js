(function() {
    'use strict';

    angular
        .module('web')
          .controller('SaveAnswerController', SaveAnswerController);

    /** @ngInject */
    function SaveAnswerController(answerService,$state) {
        var self = this;
        var setCreator = function(answer){
            answer.creator= {
                id:1
            }
        }
        self.answer = {
            question :{
                id:$state.params.questionId
            }
        }
        setCreator(self.answer);
        self.languages =[];
        self.submit = function(){
            answerService.save(self.answer);
        }
        answerService.getLanguages().success(function(languages){
            angular.copy(languages,self.languages);
        });
        if($state.params.answerId){
            answerService.getById($state.params.answerId).success(function(answer){
                angular.copy(answer,self.answer);
            });
        }
    }
})();
