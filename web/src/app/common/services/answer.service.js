(function() {
    'use strict';

    angular
        .module('web')
        .service('answerService', AnswerService);


    /** @ngInject */
    function AnswerService($http,answerUrls){
        this.get = function(){
            return $http.get(answerUrls.query);
        }

        this.getById = function(aid){
            return $http.get(answerUrls.query+"/"+aid);
        }

        this.save = function(answer){
            return $http.post(answerUrls.command,answer)
        }

        this.getLanguages = function(){
            return $http.get(answerUrls.query+"/languages");
        }
    }
})()
