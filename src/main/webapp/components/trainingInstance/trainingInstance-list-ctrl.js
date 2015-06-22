'use strict';
var app = angular.module('Fablab');
app.controller('TrainingInstanceListController', function ($scope, $filter, $location,
        ngTableParams, TrainingInstanceService, NotificationService) {
    $scope.tableParams = new ngTableParams(
            angular.extend({
                page: 1, // show first page
                count: 25, // count per page
                sorting: {
                    trainingDate:'desc'
                }
            }, $location.search()), {
        getData: function ($defer, params) {
            if ($scope.trainingInstances) {
                params.total($scope.trainingInstances.length);
                $location.search(params.url());
                var filteredData = params.filter() ? $filter('filter')($scope.trainingInstances, params.filter()) : $scope.trainingInstances;
                var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
                $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
            }
        }
    });
    var updateTrainingInstanceList = function () {
        TrainingInstanceService.list(function (data) {
            for (var i = 0; i < data.length; i++) {
                data[i].trainingName = ""; //initialization of new property 
                data[i].trainingName = data[i].training.name;  //set the data from nested obj into new property
            }
            $scope.trainingInstances = data;
            $scope.tableParams.reload();
        });
    };
    $scope.remove = function (trainingInstance) {
        TrainingInstanceService.remove(trainingInstance.id, function () {
            NotificationService.notify("success", "trainingInstance.notification.removed");
            updateTrainingInstanceList();
        });
    };
    $scope.softRemove = function (trainingInstance) {
        TrainingInstanceService.softRemove(trainingInstance.id, function () {
            NotificationService.notify("success", "trainingInstance.notification.removed");
            updateTrainingInstanceList();
        });
    };
    updateTrainingInstanceList();
});

