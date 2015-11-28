(function() {
  'use strict';

  angular
    .module('web')
    .config(routerConfig);

  /** @ngInject */
  function routerConfig($stateProvider, $urlRouterProvider) {
    $stateProvider
      .state('home', {
        url: '/',
        templateUrl: 'app/main/bookpage.html',
        controller: 'BookpageController',
        controllerAs: 'main'
      })
      .state('saveQuestion', {
        url: '/saveQuestion/:questionId',
        templateUrl: 'app/saveQuestion/savequestion.view.html',
        controller: 'SaveQuestionController',
        controllerAs: 'qform'
      })
      .state('saveAnswer', {
        url: '/:questionId/saveAnswer/:answerId',
        templateUrl: 'app/saveAnswer/saveanswer.view.html',
        controller: 'SaveAnswerController',
        controllerAs: 'ansform'
      })
      ;

    $urlRouterProvider.otherwise('/');
  }

})();
