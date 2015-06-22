'use strict';
var app = angular.module('Fablab');
app.controller('GlobalTrainingEditController', function ($scope, $location,
        TrainingService, NotificationService, StaticDataService) {
    $scope.currency = App.CONFIG.CURRENCY;
    $scope.selected = {training: undefined};
    $scope.loadTraining = function (id) {
        TrainingService.get(id, function (data) {
            $scope.training = data;
        });
    };
    $scope.save = function () {
        var trainingCurrent = angular.copy($scope.training);
        TrainingService.save(trainingCurrent, function (data) {
            $scope.training = data;
            NotificationService.notify("success", "training.notification.saved");
            $location.path("trainings");
        });
    };
    StaticDataService.loadTrainingLevels(function (data) {
        $scope.trainingLevelList = data;
    });
    StaticDataService.loadMachineTypes(function (data) {
        $scope.machineTypeList = data;
    });
}
);
app.controller('TrainingNewController', function ($scope, $controller) {
    $controller('GlobalTrainingEditController', {$scope: $scope});
    $scope.newTraining = true;
    $scope.training = new Object();
}
);
app.controller('TrainingEditController', function ($scope, $routeParams, $controller) {
    $controller('GlobalTrainingEditController', {$scope: $scope});
    $scope.newTraining = false;
    $scope.loadTraining($routeParams.id);
}
);

