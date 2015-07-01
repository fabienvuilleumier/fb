'use strict';
var app = angular.module('Fablab');
app.controller('GlobalEventModuleEditController', function ($scope, $location,
    EventModuleService, NotificationService) {
    $scope.selected = {eventModule: undefined};
    $scope.loadEventModule = function (id) {
        EventModuleService.get(id, function (data) {
            $scope.eventModule = data;
        });
    };
    $scope.save = function () {
        var eventModuleCurrent = angular.copy($scope.eventModule);
        EventModuleService.save(eventModuleCurrent, function (data) {
            $scope.eventModule = data;
            NotificationService.notify("success", "eventModule.notification.saved");
            $location.path("eventModules");
        });
    };
    EventModuleService.list(function (data) {
        var res = [];
        for (var i = 0; i < data.length; i++) {
            res.push(data[i].label.toUpperCase());
        }
        $scope.existingValues = res;
    });
}
);
app.controller('EventModuleNewController', function ($scope, $controller) {
    $controller('GlobalEventModuleEditController', {$scope: $scope});
    $scope.newEventModule = true;
    $scope.eventModule = new Object();
}
);
    app.controller('EventModuleEditController', function ($scope, $routeParams, $controller) {
    $controller('GlobalEventModuleEditController', {$scope: $scope});
    $scope.newEventModule = false;
    $scope.loadEventModule($routeParams.id);
}
);

