'use strict';
var app = angular.module('Fablab');
app.controller('MachineListController', function ($scope, $filter, $location,
        ngTableParams, MachineService, NotificationService, 
        MembershipTypeService, PriceMachineService) {
    $scope.tableParams = new ngTableParams(
            angular.extend({
                page: 1, // show first page
                count: 25, // count per page
                sorting: {
                    name: 'asc'
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
            for (var i = 0; i < data.length; i++) {
                data[i].machineTypeName = ""; //initialization of new property 
                data[i].machineTypeName = data[i].machineType.name;  //set the data from nested obj into new property
                data[i].machineStatusLabel = ""; //initialization of new property 
                data[i].machineStatusLabel = data[i].machineStatus.label;  //set the data from nested obj into new property
                data[i].machineStateLabel = ""; //initialization of new property 
                data[i].machineStateLabel = data[i].machineState.label;  //set the data from nested obj into new property
            }
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

    var updateMemberShipList = function () {
        MembershipTypeService.list(function (mst) {
            $scope.membershipTypes = mst;
            PriceMachineService.list(function (pm) {
                $scope.priceMachines = pm;
                var mti, msti, pmi;
                var getCellule = function (mtId, mstId) {
                    for (pmi = 0; pmi < pm.length; pmi++) {
                        if (pm[pmi].machineType.id === mtId &&
                                pm[pmi].membershipType.id === mstId) {
                            return pm[pmi];
                        }
                    }
                    return "";
                };
                var mtArray = [];
                var prices = {};
                for (mti = 0; mti < mt.length; mti++) {
                    var machineType = {};
                    machineType.machineType = mt[mti];
                    mtArray.push(machineType);
                    var mstArray = [];
                    for (msti = 0; msti < mst.length; msti++) {
                        var membershipType = {};
                        membershipType.price = getCellule(mt[mti].id, mst[msti].id);
                        mstArray.push(membershipType);
                    }
                    machineType.membershipTypes = mstArray;
                }
                prices.machineTypes = mtArray;
                $scope.prices = prices;
                console.log(prices);
            });
        });
    };
    updateMemberShipList();
});

