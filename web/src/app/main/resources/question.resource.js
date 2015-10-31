(function(){
    "use strict";

    angular
        .module('web')
        .factory("Question",function($resource){
            return $resource('/api/questions');
        });
})()