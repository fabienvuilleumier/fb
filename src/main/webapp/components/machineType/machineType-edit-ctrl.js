'use strict';
var app = angular.module('Fablab');
app.controller('GlobalMachineTypeEditController', function ($scope, $location,
        MachineTypeService, NotificationService, MembershipTypeService, 
        PriceMachineService) {

    $scope.selected = {machineType: undefined};

    $scope.loadMachineType = function (id) {
        MachineTypeService.get(id, function (data) {
            $scope.machineType = data;
        });
        PriceMachineService.getMachineType(id, function(data){
           $scope.priceMachines = data;
        });
    };

    MembershipTypeService.list(function (data) {
        $scope.membershipTypes = data;
    });

    $scope.save = function () {
        var machineTypeCurrent = angular.copy($scope.machineType);
        //SAVE PRICE MACHINE ???
        MachineTypeService.save(machineTypeCurrent, function (data) {
            $scope.machineType = data;
            NotificationService.notify("success", "machineType.notification.saved");
            $location.path("machineTypes");
        });
    };

    var updateTechnicalName = function () {
        var oldName = $scope.machineType.name.substring(0, $scope.machineType.name.length - 1);
        if (!$scope.machineType.technicalname) {
            $scope.machineType.technicalname = $scope.machineType.name;
        } else {
            if ($scope.machineType.technicalname === oldName) {
                $scope.machineType.technicalname = $scope.machineType.name;
            }
        }
    };

    var initialName = function () {
        return $scope.machineType.name;
    };

    $scope.$watch(initialName, updateTechnicalName);
}
);

app.controller('MachineTypeNewController', function ($scope, $controller) {
    $controller('GlobalMachineTypeEditController', {$scope: $scope});
    $scope.newMachineType = true;
    $scope.machineType = new Object();
}
);
app.controller('MachineTypeEditController', function ($scope, $routeParams, $controller) {
    $controller('GlobalMachineTypeEditController', {$scope: $scope});
    $scope.newMachineType = false;
    $scope.loadMachineType($routeParams.id);
}
);