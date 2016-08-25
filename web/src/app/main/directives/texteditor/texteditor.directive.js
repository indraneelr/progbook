(function(){
    "use strict";

    angular
    .module('web')
    .directive("texteditor",texteditor);

    /** @ngInject */
    function texteditor($window){
        return {
            restrict:"AE",
            templateUrl:"/app/main/directives/texteditor/texteditor.view.html",
            scope:{
                content:"="
            },
            link:function($scope,elm,attr){
                var md = $window.markdownit();
                $scope.parsedContent = { asMarkdown:""};
                $scope.$watch("parsedContent.asMarkdown",function(newVal,oldVal){
                    if(oldVal !== newVal){
                        $scope.updatePreview();
                    }
                });
                $scope.updatePreview = function(){
                    var textToRender = $scope.parsedContent.asMarkdown || "";
                    $scope.parsedContent.asHtml= md.render(textToRender);
                    $scope.content = JSON.stringify($scope.parsedContent);
//                    hljs.highlightBlock(elm[0]);
                }
                $scope.$watch("content",function(newVal,oldVal){
                    if(oldVal !== newVal){
                        try{
                            var parsedJson = JSON.parse($scope.content)||{};
                            angular.copy(parsedJson,$scope.parsedContent);
                        }
                        catch(ex){
                            $scope.parsedContent.asMarkdown = $scope.content;
                            $scope.updatePreview();
                        }
                    }
                });

            },
            controllerAs:"vm"
        }
    };
})()
