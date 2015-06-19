'use strict';
var app = angular.module('Fablab');
app.controller('GlobalPurchaseEditController', function ($scope, $location,
        PurchaseService, NotificationService, StaticDataService) {
    $scope.selected = {purchase: undefined};
    $scope.loadPurchase = function (id) {
        PurchaseService.get(id, function (data) {
            $scope.purchase = data;
        });
    };
    $scope.save = function () {
        var purchaseCurrent = angular.copy($scope.purchase);
        updateStock();
        PurchaseService.save(purchaseCurrent, function (data) {
            $scope.purchase = data;
            NotificationService.notify("success", "purchase.notification.saved");
            $location.path("purchases");
        });
    };
    
    var updateStock = function(){
        var stockInit = $scope.purchase.supply.quantityStock;
        $scope.purchase.supply.quantityStock = parseFloat(stockInit) - parseFloat($scope.purchase.quantity);
    };

    $scope.maxMoney = function () {
        return parseFloat($scope.purchase.quantity) * parseFloat($scope.purchase.supply.sellingPrice);
    };

    $scope.updatePrice = function () {
        var interTotal = parseFloat($scope.purchase.quantity) * parseFloat($scope.purchase.supply.sellingPrice);
        if ($scope.purchase.discount === undefined) {
            $scope.purchase.purchasePrice = interTotal;
        } else {
            if ($scope.purchase.discountPercent) {
                var discountInter = parseFloat(interTotal) * (parseFloat($scope.purchase.discount) / parseFloat(100));
                var discountFinal = parseFloat(interTotal) - parseFloat(discountInter);
                $scope.purchase.purchasePrice = (Math.ceil(discountFinal * 20) / 20).toFixed(2);
            } else {
                $scope.purchase.purchasePrice = parseFloat(interTotal) - parseFloat($scope.purchase.discount);
            }
        }

    };

    $scope.firstPercent = App.CONFIG.FIRST_PERCENT === "PERCENT";
    $scope.optionsPercent = [{
            name: "%",
            value: true
        }, {
            name: App.CONFIG.CURRENCY,
            value: false
        }];

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

    StaticDataService.loadSupplyStock(function (data) {
        $scope.supplyStock = data;
    });
}
);
app.controller('PurchaseNewController', function ($scope, $controller, $rootScope) {
    $controller('GlobalPurchaseEditController', {$scope: $scope});
    $scope.newPurchase = true;
    $scope.purchase = {
        purchaseDate: new Date(),
        user: $rootScope.connectedUser.user
    };
}
);
app.controller('PurchaseEditController', function ($scope, $routeParams, $controller) {
    $controller('GlobalPurchaseEditController', {$scope: $scope});
    $scope.newPurchase = false;
    $scope.loadPurchase($routeParams.id);
}
);

