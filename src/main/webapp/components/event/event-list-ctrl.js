'use strict';
var app = angular.module('Fablab');
app.controller('EventListController', function ($scope, $filter, $location,
        ngTableParams, EventService, NotificationService) {
            $scope.currency = App.CONFIG.CURRENCY;
    $scope.tableParams = new ngTableParams(
            angular.extend({
                page: 1, // show first page
                count: 25, // count per page
                sorting: {
                    title:'asc'
                }
            }, $location.search()), {
        getData: function ($defer, params) {
            if ($scope.events) {
                params.total($scope.events.length);
                $location.search(params.url());
                var filteredData = params.filter() ? $filter('filter')($scope.events, params.filter()) : $scope.events;
                var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
                $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
            }
        }
    });
    var updateEventList = function () {
        EventService.list(function (data) {
            for (var i = 0; i < data.length; i++) {
                data[i].eventTypeLabel = ""; //initialization of new property 
                data[i].eventTypeLabel = data[i].eventType.label;  //set the data from nested obj into new property
            }
            $scope.events = data;
            $scope.tableParams.reload();
        });
    };
    $scope.remove = function (event) {
        EventService.remove(event.id, function () {
            NotificationService.notify("success", "event.notification.removed");
            updateEventList();
        });
    };
    $scope.softRemove = function (event) {
        EventService.softRemove(event.id, function () {
            NotificationService.notify("success", "event.notification.removed");
            updateEventList();
        });
    };
    updateEventList();
});

