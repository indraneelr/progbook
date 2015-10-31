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
      .state('addQuestion', {
        url: '/addQuestion',
        templateUrl: 'app/addQuestion/addquestion.view.html',
        controller: 'AddQuestionController',
        controllerAs: 'qform'
      });

    $urlRouterProvider.otherwise('/');
  }

})();
