'use strict';
var app = angular.module('Fablab');
app.controller('GlobalSupplyEditController', function ($scope, $location,
        SupplyService, NotificationService, StaticDataService) {
    $scope.selected = {supply: undefined};
    $scope.loadSupply = function (id) {
        SupplyService.get(id, function (data) {
            $scope.supply = data;
        });
    };
    $scope.save = function () {
        var supplyCurrent = angular.copy($scope.supply);
        SupplyService.save(supplyCurrent, function (data) {
            $scope.supply = data;
            NotificationService.notify("success", "supply.notification.saved");
            $location.path("supplies");
        });
    };

    StaticDataService.loadSupplyTypes(function (data) {
        $scope.supplyTypeList = data;
    });
    
    StaticDataService.loadSupplyUnities(function (data) {
        $scope.unityList = data;
    });
}
);
app.controller('SupplyNewController', function ($scope, $controller) {
    $controller('GlobalSupplyEditController', {$scope: $scope});
    $scope.newSupply = true;
    $scope.supply = new Object();
}
);
app.controller('SupplyEditController', function ($scope, $routeParams, $controller) {
    $controller('GlobalSupplyEditController', {$scope: $scope});
    $scope.newSupply = false;
    $scope.loadSupply($routeParams.id);
}
);

