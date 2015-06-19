'use strict';
var app = angular.module('Fablab');
app.controller('SupplyListController', function ($scope, $filter, $location, $modal,
        ngTableParams, SupplyService, NotificationService) {
    $scope.newQuantity = 0;
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

    var addQuantity = function (supplyId, quantity) {
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

    $scope.open = function (data) {

        var modalInstance = $modal.open({
            animation: true,
            templateUrl: 'modalContent.html',
            controller: 'ModalInstanceCtrl',
            resolve: {
                supply: function () {
                    return data;
                }
            }
        });

        modalInstance.result.then(function (res) {
            addQuantity(res.sup.id, res.newQty);
        });
    };
});


app.controller('ModalInstanceCtrl', function ($scope, $modalInstance, supply) {
    $scope.supply = supply;
    $scope.newQuantity = 0;
    $scope.ok = function () {
        $modalInstance.close({sup : $scope.supply, newQty: $scope.newQuantity});
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});

