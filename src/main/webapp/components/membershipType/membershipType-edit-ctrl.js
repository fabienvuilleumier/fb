'use strict';
var app = angular.module('Fablab');
app.controller('GlobalMembershipTypeEditController', function ($scope, $location,
    MembershipTypeService, NotificationService) {
    $scope.selected = {membershipType: undefined};
    $scope.loadMembershipType = function (id) {
        MembershipTypeService.get(id, function (data) {
            $scope.membershipType = data;
        });
    };
    $scope.save = function () {
        var membershipTypeCurrent = angular.copy($scope.membershipType);
        MembershipTypeService.save(membershipTypeCurrent, function (data) {
            $scope.membershipType = data;
            NotificationService.notify("success", "membershipType.notification.saved");
            $location.path("membershipTypes");
        });
    };
}
);
app.controller('MembershipTypeNewController', function ($scope, $controller) {
    $controller('GlobalMembershipTypeEditController', {$scope: $scope});
    $scope.newMembershipType = true;
    $scope.membershipType = new Object();
}
);
    app.controller('MembershipTypeEditController', function ($scope, $routeParams, $controller) {
    $controller('GlobalMembershipTypeEditController', {$scope: $scope});
    $scope.newMembershipType = false;
    $scope.loadMembershipType($routeParams.id);
}
);

