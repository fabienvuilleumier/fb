'use strict';
var app = angular.module('Fablab');
app.controller('RoleController', function ($scope,
        NotificationService, RoleService, GroupService) {

    $scope.group;
    GroupService.list(function (groupes) {
        $scope.groupList = groupes;
    });

    $scope.setLists = function () {
        RoleService.list(function (roles) {
            if ($scope.group) {
                $scope.availableRoles = roles;
                $scope.assignedRoles = $scope.group.roles;
            }
        });
    };

    $scope.save = function () {
        $scope.group.roles = $scope.assignedRoles;
        var groupCurrent = angular.copy($scope.group);
        GroupService.save(groupCurrent, function (data) {
            $scope.group = data;
            NotificationService.notify("success", "role.notification.saved");
        });
    };
    $scope.settings = {
        bootstrap2: false,
        moveOnSelect: false,
        postfix: '_helperz',
        selectMinHeight: 200,
        filter: true,
        filterValues: true
    };
    $scope.setLists();
});

