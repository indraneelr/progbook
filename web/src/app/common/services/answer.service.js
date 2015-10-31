(function() {
    'use strict';

    angular
        .module('web')
        .service('AnswerService', AnswerService);


    /** @ngInject */
    function AnswerService($http,answerUrls){
        this.get = function(){
            return $http.get(answerUrls.query);
        }

        this.getById = function(qid){
            return $http.get(answerUrls.query+"/"+qid);
        }

        this.save = function(answer){
            return $http.post(answerUrls.command,answer)
        }
    }
})()
