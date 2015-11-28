(function() {
    'use strict';

    angular
        .module('web')
          .controller('SaveAnswerController', SaveAnswerController);

    /** @ngInject */
    function SaveAnswerController(answerService,$stateParams) {
        var self = this;
        var setCreator = function(answer){
            answer.creator= {
                id:1
            }
        }
        self.answer = {
            question :{
                id:$stateParams.questionId
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
        if($stateParams.answerId){
            answerService.getById($stateParams.answerId).success(function(answer){
                angular.copy(answer,self.answer);
            });
        }
    }
})();
