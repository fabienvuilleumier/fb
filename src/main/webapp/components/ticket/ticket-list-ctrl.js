'use strict';
var app = angular.module('Fablab');
app.controller('TicketListController', function ($scope, $filter, $location,
        ngTableParams, TicketService, NotificationService) {
    $scope.tableParams = new ngTableParams(
            angular.extend({
                page: 1, // show first page
                count: 25, // count per page
                sorting: {
                    title: 'asc',
                    creationDate: 'asc',
                    previsionCloseDate: 'asc',
                    closeDate: 'asc',
                    machine: 'asc',
                    status: 'asc'
                }
            }, $location.search()), {
        getData: function ($defer, params) {
            if ($scope.tickets) {
                params.total($scope.tickets.length);
                $location.search(params.url());
                var filteredData = params.filter() ? $filter('filter')($scope.tickets, params.filter()) : $scope.tickets;
                var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
                $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
            }
        }
    });
    var updateTicketList = function () {
        TicketService.list(function (data) {
            $scope.tickets = data;
            $scope.tableParams.reload();
        });
    };
    $scope.remove = function (ticket) {
        TicketService.remove(ticket.id, function () {
            NotificationService.notify("success", "ticket.notification.removed");
            updateTicketList();
        });
    };
    $scope.softRemove = function (ticket) {
        TicketService.softRemove(ticket.id, function () {
            NotificationService.notify("success", "ticket.notification.removed");
            updateTicketList();
        });
    };
    updateTicketList();
});

