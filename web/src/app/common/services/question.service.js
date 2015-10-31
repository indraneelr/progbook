(function() {
    'use strict';

    angular
        .module('web')
        .service('questionService',questionService);


   /** @ngInject */
    function questionService($http,questionUrls){
         var self = this;
         self.get = function(){
             return $http.get(questionUrls.query);
         }

         self.getById = function(qid){
             return $http.get(questionUrls.query+"/"+qid);
         }

         self.save = function(question){
             return $http.post(questionUrls.command,question)
         }

         self.getCategories = function(){
            return $http.get(questionUrls.query+"/categories");
         }
    }
})()

