'use strict';
var app = angular.module('Fablab');
app.controller('GlobalEventPersonEditController', function ($scope, $location,
    EventPersonService, NotificationService) {
    $scope.selected = {eventPerson: undefined};
    $scope.loadEventPerson = function (id) {
        EventPersonService.get(id, function (data) {
            $scope.eventPerson = data;
        });
    };
    $scope.save = function () {
        var eventPersonCurrent = angular.copy($scope.eventPerson);
        EventPersonService.save(eventPersonCurrent, function (data) {
            $scope.eventPerson = data;
            NotificationService.notify("success", "eventPerson.notification.saved");
            $location.path("eventPersons");
        });
    };
    EventPersonService.list(function (data) {
        var res = [];
        for (var i = 0; i < data.length; i++) {
            res.push(data[i].label.toUpperCase());
        }
        $scope.existingValues = res;
    });
}
);
app.controller('EventPersonNewController', function ($scope, $controller) {
    $controller('GlobalEventPersonEditController', {$scope: $scope});
    $scope.newEventPerson = true;
    $scope.eventPerson = new Object();
}
);
    app.controller('EventPersonEditController', function ($scope, $routeParams, $controller) {
    $controller('GlobalEventPersonEditController', {$scope: $scope});
    $scope.newEventPerson = false;
    $scope.loadEventPerson($routeParams.id);
}
);

