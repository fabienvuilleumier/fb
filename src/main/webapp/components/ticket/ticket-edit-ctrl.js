'use strict';
var app = angular.module('Fablab');
app.controller('GlobalTicketEditController', function ($scope, $rootScope, $location,
        TicketService, NotificationService, StaticDataService, TicketStatusService) {
    $scope.selected = {ticket: undefined};
    $scope.loadTicket = function (id) {
        TicketService.get(id, function (data) {
            $scope.ticket = data;
        });

    };
    $scope.save = function () {
        var ticketCurrent = angular.copy($scope.ticket);
        TicketService.save(ticketCurrent, function (data) {
            $scope.ticket = data;
            NotificationService.notify("success", "ticket.notification.saved");
            $location.path("tickets");
        });
    };

    $scope.closeTicket = function () {
        if (!$scope.newTicket) {
            $scope.ticket.closeDate = new Date();
            $scope.ticket.closeUser = $rootScope.connectedUser.user;
            TicketStatusService.findByLabel("CLOSED", function (data) {
                $scope.ticket.status = data;
                $scope.save();
            });
        }
    };
    
    $scope.reOpenTicket = function () {
        if (!$scope.newTicket) {
            $scope.ticket.closeDate = null;
            $scope.ticket.closeUser = null;
            TicketStatusService.findByLabel("OPEN", function (data) {
                $scope.ticket.status = data;
                $scope.save();
            });
        }
    };

    StaticDataService.loadTicketStatus(function (data) {
        $scope.ticketStatusList = data;
    });
    StaticDataService.loadMachineries(function (data) {
        $scope.machines = data;
    });

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

    $scope.minDate = new Date();
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

}
);
app.controller('TicketNewController', function ($scope, $controller, $rootScope, TicketStatusService) {
    $controller('GlobalTicketEditController', {$scope: $scope});
    $scope.newTicket = true;
    $scope.ticket = {
        creationDate: new Date(),
        creationUser: $rootScope.connectedUser.user
    };
    TicketStatusService.findByLabel("OPEN", function (data) {
        $scope.ticket.status = data;
    });
}
);
app.controller('TicketEditController', function ($scope, $routeParams, $controller) {
    $controller('GlobalTicketEditController', {$scope: $scope});
    $scope.newTicket = false;
    $scope.loadTicket($routeParams.id);
}
);

