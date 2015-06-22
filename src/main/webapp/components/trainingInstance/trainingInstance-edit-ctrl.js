'use strict';
var app = angular.module('Fablab');
app.controller('GlobalTrainingInstanceEditController', function ($scope, $location,
    TrainingInstanceService, NotificationService, StaticDataService) {
    $scope.selected = {trainingInstance: undefined};
    $scope.loadTrainingInstance = function (id) {
        TrainingInstanceService.get(id, function (data) {
            $scope.trainingInstance = data;
        });
    };
    $scope.save = function () {
        var trainingInstanceCurrent = angular.copy($scope.trainingInstance);
        TrainingInstanceService.save(trainingInstanceCurrent, function (data) {
            $scope.trainingInstance = data;
            NotificationService.notify("success", "trainingInstance.notification.saved");
            $location.path("trainingInstances");
        });
    };
    $scope.minDate = new Date();
    $scope.today = function () {
        $scope.dt = new Date();
    };
    $scope.today();
    $scope.clear = function () {
        $scope.dt = null;
    };
    $scope.open = function ($event) {
        $event.preventDefault();
        $event.stopPropagation();
        $scope.opened = true;
    };
    $scope.dateOptions = {
        formatYear: 'yy',
        startingDay: 1
    };
    $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
    $scope.format = $scope.formats[2];
    var tomorrow = new Date();
    tomorrow.setDate(tomorrow.getDate() + 1);
    var afterTomorrow = new Date();
    afterTomorrow.setDate(tomorrow.getDate() + 2);
    $scope.events =
        [
            {
                date: tomorrow,
                status: 'full'
            },
            {
                date: afterTomorrow,
                status: 'partially'
            }
        ];
    $scope.getDayClass = function (date, mode) {
        if (mode === 'day') {
            var dayToCheck = new Date(date).setHours(0, 0, 0, 0);
            for (var i = 0; i < $scope.events.length; i++) {
                var currentDay = new Date($scope.events[i].date).setHours(0, 0, 0, 0);
                if (dayToCheck === currentDay) {
                    return $scope.events[i].status;
                }
            }
        }
        return '';
    };
    StaticDataService.loadTrainings(function (data) {
        $scope.trainingList = data;
    });
}
);
app.controller('TrainingInstanceNewController', function ($scope, $controller) {
    $controller('GlobalTrainingInstanceEditController', {$scope: $scope});
    $scope.newTrainingInstance = true;
    $scope.trainingInstance = new Object();
}
);
    app.controller('TrainingInstanceEditController', function ($scope, $routeParams, $controller) {
    $controller('GlobalTrainingInstanceEditController', {$scope: $scope});
    $scope.newTrainingInstance = false;
    $scope.loadTrainingInstance($routeParams.id);
}
);

