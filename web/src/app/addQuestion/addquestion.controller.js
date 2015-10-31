(function() {
    'use strict';

    angular
        .module('web')
          .controller('AddQuestionController', AddQuestionController);

    /** @ngInject */
    function AddQuestionController(questionService) {
        var self = this;
        self.question = {}
        self.categories =[];
        self.submit = function(){
            questionService.save(self.question);
        }
        questionService.getCategories().success(function(categories){
            angular.copy(categories,self.categories);
        });
    }
})();
