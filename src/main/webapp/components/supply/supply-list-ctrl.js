'use strict';
var app = angular.module('Fablab');
app.controller('SupplyListController', function ($scope, $filter, $location,
        ngTableParams, SupplyService, NotificationService) {
    $scope.currency = App.CONFIG.CURRENCY;
    $scope.tableParams = new ngTableParams(
            angular.extend({
                page: 1, // show first page
                count: 25, // count per page
                sorting: {
                    code: 'asc',
                    label: 'asc',
                    sellingPrice: 'asc',
                    unityBuyingPrice: 'asc',
                    orderAddress: 'asc',
                    supplyType: 'asc', 
                    supplyUnity: 'asc'
                }
            }, $location.search()), {
        getData: function ($defer, params) {
            if ($scope.supplies) {
                params.total($scope.supplies.length);
                $location.search(params.url());
                var filteredData = params.filter() ? $filter('filter')($scope.supplies, params.filter()) : $scope.supplies;
                var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
                $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
            }
        }
    });
    var updateSupplyList = function () {
        SupplyService.list(function (data) {
            $scope.supplies = data;
            $scope.tableParams.reload();
        });
    };
    $scope.remove = function (supply) {
        SupplyService.remove(supply.id, function () {
            NotificationService.notify("success", "supply.notification.removed");
            updateSupplyList();
        });
    };
    $scope.newQuantity = 0;
    $scope.addQuantity = function (supplyId, quantity) {
        SupplyService.addQuantity(supplyId, quantity, function () {
            NotificationService.notify("success", "supply.notification.addQuantity");
            updateSupplyList();
        });
    };
    $scope.softRemove = function (supply) {
        SupplyService.softRemove(supply.id, function () {
            NotificationService.notify("success", "supply.notification.removed");
            updateSupplyList();
        });
    };
    updateSupplyList();
});

