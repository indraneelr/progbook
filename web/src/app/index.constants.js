/* global malarkey:false, moment:false */
(function() {
  'use strict';

  angular
    .module('web')
    .constant('malarkey', malarkey)
    .constant('moment', moment)
    .constant('questionUrls',{
        query:"/api/questions",
        command:"/api/questions"
    })
    .constant('answerUrls',{
            query:"/api/answers",
            command:"/api/answers"
    });

})();
