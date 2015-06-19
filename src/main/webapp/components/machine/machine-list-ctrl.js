'use strict';
var app = angular.module('Fablab');
app.controller('MachineListController', function ($scope, $filter, $location,
        ngTableParams, MachineService, NotificationService) {
    $scope.tableParams = new ngTableParams(
            angular.extend({
                page: 1, // show first page
                count: 25, // count per page
                sorting: {
                    name: 'asc',
                    buyPrice: 'asc',
                    acquisitionDate: 'asc',
                    machineType: 'asc',
                    machineStatus: 'asc',
                    machineState: 'asc',
                    code: 'asc'
                }
            }, $location.search()), {
        getData: function ($defer, params) {
            if ($scope.machines) {
                params.total($scope.machines.length);
                $location.search(params.url());
                var filteredData = params.filter() ? $filter('filter')($scope.machines, params.filter()) : $scope.machines;
                var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
                $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
            }
        }
    });
    var updateMachineList = function () {
        MachineService.list(function (data) {
            $scope.machines = data;
            $scope.tableParams.reload();
        });
    };
    $scope.remove = function (machine) {
        MachineService.remove(machine.id, function () {
            NotificationService.notify("success", "machine.notification.removed");
            updateMachineList();
        });
    };
    $scope.softRemove = function (machine) {
        MachineService.softRemove(machine.id, function () {
            NotificationService.notify("success", "machine.notification.removed");
            updateMachineList();
        });
    };
    $scope.currency = App.CONFIG.CURRENCY;
    updateMachineList();
});

