(function(){
    "use strict";

    angular
    .module('web')
    .directive("texteditor",texteditor);

    /** @ngInject */
    function texteditor($window){

/*    controller:function($scope){
                    var vm= this;
                    var md = $window.markdownit();
                    try{
                        vm.content = JSON.parse($scope.content)||{};
                    }
                    catch(ex){
                        vm.content = {
                            asMarkdown:$scope.content
                        }
                    }
                    vm.updatePreview = function(){
                        vm.content.asHtml= md.render(vm.content.asMarkdown)
                        $scope.content = JSON.stringify(vm.content)
                    }
                },*/
        return {
            restrict:"AE",
            templateUrl:"/app/main/directives/texteditor/texteditor.view.html",
            scope:{
                content:"="
            },
            link:function($scope,elm,attr){
                var md = $window.markdownit();
                $scope.parsedContent = { asMarkdown:""};
                $scope.$watch("parsedContent.asMarkdown",function(oldVal,newVal){
                    if(oldVal !== newVal){
                        $scope.updatePreview();
                    }
                });
                $scope.updatePreview = function(){
                    $scope.parsedContent.asHtml= md.render($scope.parsedContent.asMarkdown)
                    $scope.content = JSON.stringify($scope.parsedContent)
//                    hljs.highlightBlock(elm[0]);
                }
                try{
                    var parsedJson = JSON.parse($scope.content)||{};
                    angular.copy(parsedJson,$scope.parsedContent);
                }
                catch(ex){
                    $scope.parsedContent.asMarkdown = $scope.content;
                    $scope.updatePreview();
                }
            },
            controllerAs:"vm"
        }
    };
})()
