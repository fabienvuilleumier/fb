'use strict';
var app = angular.module('Fablab');
app.controller('GlobalEventEditController', function ($scope, $location,
        EventService, NotificationService, StaticDataService, EventTypeService,
        EventPersonService, UserService) {
    $scope.currency = App.CONFIG.CURRENCY;
    $scope.selected = {event: undefined};
    $scope.loadEvent = function (id) {
        EventService.get(id, function (data) {
            $scope.event = data;
            if ($scope.event.dateStart) {
                $scope.event.dateStart = new Date($scope.event.dateStart);
            }
            if ($scope.event.dateEnd) {
                $scope.event.dateEnd = new Date($scope.event.dateEnd);
            }
            if ($scope.event.timeStart) {
                $scope.event.timeStart = new Date($scope.event.timeStart);
            }
            if ($scope.event.timeEnd) {
                $scope.event.timeEnd = new Date($scope.event.timeEnd);
            }
            setLists();
        });
    };
    $scope.save = function () {
        $scope.event.organizers = $scope.assignedOrganizers;
        $scope.event.participants = $scope.assignedParticipants;
        var eventCurrent = angular.copy($scope.event);
        console.log(eventCurrent);
        EventService.save(eventCurrent, function (data) {
            $scope.event = data;
            NotificationService.notify("success", "event.notification.saved");
            $location.path("events");
        });
    };
    $scope.saveEventPerson = function () {
        var person = angular.copy($scope.newPerson);
        EventPersonService.save(person, function (data) {
            $scope.person = data;
            NotificationService.notify("success", "event.notification.saved");
            setLists();
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
    StaticDataService.loadSupervisors(function (data) {
        $scope.supervisorList = data;
    });
    EventTypeService.list(function (data) {
        $scope.eventTypeList = data;
    });
    EventService.list(function (data) {
        var res = [];
        for (var i = 0; i < data.length; i++) {
            res.push(data[i].title.toUpperCase());
        }
        $scope.existingValues = res;
    });
    EventPersonService.list(function (data) {
        var res = [];
        for (var i = 0; i < data.length; i++) {
            res.push(data[i].email.toUpperCase());
        }
        $scope.existingValuesEmail = res;
    });

    $scope.newPerson;
    $scope.hstep = 1;
    $scope.mstep = 15;

    $scope.settings = {
        bootstrap2: false,
        moveOnSelect: true,
        postfix: '_helperz',
        selectMinHeight: 200,
        filter: true,
        filterValues: true
    };

    var setLists = function () {
        EventPersonService.list(function (eventPerson) {
            if ($scope.event) {
                $scope.availableOrganizers = eventPerson;
                $scope.assignedOrganizers = $scope.event.organizers;
                $scope.availableParticipants = eventPerson;
                $scope.assignedParticipants = $scope.event.participants;
            }
        });
    };
}
);
app.controller('EventNewController', function ($scope, $controller) {
    $controller('GlobalEventEditController', {$scope: $scope});
    $scope.newEvent = true;
    $scope.event = {
        dateStart: new Date(),
        dateEnd: new Date(),
        timeStart: new Date(),
        timeEnd: new Date()
    };
}
);
app.controller('EventEditController', function ($scope, $routeParams, $controller) {
    $controller('GlobalEventEditController', {$scope: $scope});
    $scope.newEvent = false;
    $scope.loadEvent($routeParams.id);
}
);

