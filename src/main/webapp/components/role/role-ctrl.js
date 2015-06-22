'use strict';
var app = angular.module('Fablab');
app.controller('RoleController', function ($scope, $location,
        NotificationService) {
            
            
            
            

    $scope.save = function () {
        var mt = $scope.prices.machineTypes;
        var mti, msti;
        for (mti = 0; mti < mt.length; mti++) {
            var mst = mt[mti].membershipTypes;
            for (msti = 0; msti < mst.length; msti++) {
                var price = mst[msti].price;
                var priceMachineCurrent = angular.copy(price);
                PriceMachineService.save(priceMachineCurrent, function (data) {
                    price = data;
                    NotificationService.notify("success", "priceMachine.notification.saved");
                    $location.path("priceMachines/table");
                });
            }
        }
    };

});

