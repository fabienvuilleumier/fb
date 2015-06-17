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
        PurchaseService.save(purchaseCurrent, function (data) {
            $scope.purchase = data;
            NotificationService.notify("success", "purchase.notification.saved");
            $location.path("purchases");
        });
    };

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
        user :$rootScope.connectedUser.user
    };
}
);
app.controller('PurchaseEditController', function ($scope, $routeParams, $controller) {
    $controller('GlobalPurchaseEditController', {$scope: $scope});
    $scope.newPurchase = false;
    $scope.loadPurchase($routeParams.id);
}
);

