(function () {
    'use strict';

    angular.module('Fablab').controller('ReservationListController', function ($scope, $filter,
            $location, ngTableParams, ReservationService, NotificationService) {
        $scope.criteria = {
            dateFrom: moment().startOf('month').toDate(),
            dateTo: moment().endOf('month').toDate()
        };

        $scope.tableParams = new ngTableParams(
                angular.extend({
                    page: 1, // show first page
                    count: 25, // count per page
                    sorting: {
                        dateStart: 'asc'
                    }
                }, $location.search()), {
            getData: function ($defer, params) {
                if ($scope.reservations) {
                    var filteredData = params.filter() ? $filter('filter')($scope.reservations, params.filter()) : $scope.reservations;
                    var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
                    $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
                }
            }
        });


        $scope.updateReservationList = function () {
            var criteria = {
                dateFrom: $filter('cropToDate')($scope.criteria.dateFrom),
                dateTo: $filter('cropToDate')($scope.criteria.dateTo)
            };
            ReservationService.search(criteria, function (data) {
                for (var i = 0; i < data.length; i++) {
                    data[i].machineName = ""; //initialization of new property 
                    data[i].machineName = data[i].machine.name;  //set the data from nested obj into new property
                    data[i].userName = ""; //initialization of new property 
                    data[i].userName = data[i].user === null ? "FabLab" : $filter('prettyUser')(data[i].user);  //set the data from nested obj into new property
                }
                $scope.reservations = data;
                $scope.tableParams.reload();
                updateCalendarEvent();
            });
        };

        var updateCalendarEvent = function () {
            $scope.events.length = 0;
            for (var i = 0; i < $scope.reservations.length; i++) {
                var r = $scope.reservations[i];
                console.log("r " + r);
                $scope.events.push({
                    title: r.machine.name,
                    start: new Date(r.dateStart),
                    end: new Date(r.dateEnd)});
            }
        };

        $scope.remove = function (reservation) {
            ReservationService.remove(reservation.reservationId, function () {
                $scope.updateReservationList();
            });
        };

        $scope.softRemove = function (reservation) {
            ReservationService.softRemove(reservation.id, function () {
                NotificationService.notify("success", "reservation.notification.removed");
                $scope.updateReservationList();
            });
        };

        
        var date = new Date();
        var d = date.getDate();
        var m = date.getMonth();
        var y = date.getFullYear();

        /* event source that pulls from google.com */
        $scope.dataSourceGoogleAgenda = {
            url: App.CONFIG.GOOGLE_CALENDAR_URL,
            className: 'gcal-event',
            currentTimezone: App.CONFIG.CALENDAR_TIME_ZONE,
            color:  App.CONFIG.CALENDAR_AGENDA_COLOR,
            textColor: 'black'
        };
        
        //$scope.events=[];
        /* event source that contains custom events on the scope */
        /*$scope.events = [
            {title: 'All Day Event', start: new Date(y, m, 1)},
            {title: 'Long Event', start: new Date(y, m, d - 5), end: new Date(y, m, d - 2)},
            {id: 999, title: 'Repeating Event', start: new Date(y, m, d - 3, 16, 0), allDay: false},
            {id: 999, title: 'Repeating Event', start: new Date(y, m, d + 4, 16, 0), allDay: false},
            {title: 'Birthday Party', start: new Date(y, m, d + 1, 19, 0), end: new Date(y, m, d + 1, 22, 30), allDay: false},
            {title: 'Click for Google', start: new Date(y, m, 28), end: new Date(y, m, 29), url: 'http://google.com/'}
        ];*/
        $scope.updateReservationList();

        $scope.dataSourceReservation = {
            events: $scope.events,
            color: App.CONFIG.CALENDAR_RESERVATION_COLOR
        };

        /* config object */
        $scope.uiConfig = {
            calendar: {
                googleCalendarApiKey: App.CONFIG.GOOGLE_CALENDAR_API_KEY,
                height: 450,
                editable: false,
                firstDay: 1,
                header: {
                    left: 'title',
                    center: 'month,agendaWeek,agendaDay',
                    right: 'today prev,next'
                }
            }
        };

        /* event sources array*/
        $scope.eventSources = [$scope.dataSourceGoogleAgenda, $scope.dataSourceReservation];

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
    });


}());