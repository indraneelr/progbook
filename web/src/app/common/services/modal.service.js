(function() {
    'use strict';

    angular
        .module('web')
          .service('modalService', modalService);

    /** @ngInject */
    function modalService($uibModal,$log) {
        var self =this;
        self.openSaveQuestionModal = function (question) {
            var modalInstance = $uibModal.open({
                animation: 'true',
                templateUrl: '/app/saveQuestion/savequestion.view.html',
                controller: 'SaveQuestionController',
                controllerAs: 'qform',
                size: 'lg',
                resolve:{
                    questionToSave:question
                }
            });

            modalInstance.result.then(function (selectedItem) {
//                $scope.selected = selectedItem;
            },  function () {
                $log.info('Modal dismissed at: ' + new Date());
            });
        };

        self.openSaveQuestionModal = function (question) {
                    var modalInstance = $uibModal.open({
                        animation: 'true',
                        templateUrl: '/app/saveAnswer/saveanswer.view.html',
                        controller: 'SaveAnswerController',
                        controllerAs: 'qform',
                        size: 'lg',
                        resolve:{
                            questionToSave:question
                        }
                    });

                    modalInstance.result.then(function (selectedItem) {
        //                $scope.selected = selectedItem;
                    },  function () {
                        $log.info('Modal dismissed at: ' + new Date());
                    });
                };
    }
})();
