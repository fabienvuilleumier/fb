'use strict';
var app = angular.module('Fablab');
app.controller('PriceMachineTableController', function ($scope, $filter, $location,
        ngTableParams, PriceMachineService, MachineTypeService, MembershipTypeService) {
    $scope.tableParams = new ngTableParams(
            angular.extend({
                page: 1, // show first page
                count: 25, // count per page
                sorting: {
                    price: 'asc'
                }
            }, $location.search()), {
        getData: function ($defer, params) {
            if ($scope.priceMachines) {
                params.total($scope.priceMachines.length);
                $location.search(params.url());
                var filteredData = params.filter() ? $filter('filter')($scope.priceMachines, params.filter()) : $scope.priceMachines;
                var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
                $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
            }
        }
    });

    $scope.getCurrentPrice = function (machineTypeId, membershipTypeId) {
        var temp = {};
        var defer = $q.defer();
        $http.get(App.API.PRICE_MACHINE_API + "/getPriceMachineType?machineTypeId="
                + machineTypeId + "&membershipTypeId=" + membershipTypeId).success(function (data) {
            temp = data;
            defer.resolve(data);
        });
        return defer.promise;
    };

    var updatePriceMachineList = function () {
        MachineTypeService.list(function (data) {
            $scope.machineTypes = data;
        });
        MembershipTypeService.list(function (data) {
            $scope.membershipTypes = data;
        });
        console.log($scope.machineTypes);
        console.log($scope.membershipTypes);



        PriceMachineService.list(function (data) {
            for (var i = 0; i < data.length; i++) {
                data[i].membershipTypeName = ""; //initialization of new property 
                data[i].membershipTypeName = data[i].membershipType.name;  //set the data from nested obj into new property
            }
            $scope.priceMachines = data;
            $scope.tableParams.reload();
        });
    };
    $scope.getPMT = function (machineTypeId, membershipTypeId) {
        return PriceMachineService.getPMT(machineTypeId, membershipTypeId);
    };
    updatePriceMachineList();
});

