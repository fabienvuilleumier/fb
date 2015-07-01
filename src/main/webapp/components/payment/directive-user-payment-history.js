(function () {
    'use strict';
    angular.module('Fablab').directive('userPaymentHistory', function (AccountingService, UserService, $filter) {
        return {
            restrict: 'EA',
            scope: {
                user: '=?',
                reload: '=?',
                editable: '=?',
                needReloadUser: '&'
            },
            templateUrl: 'components/payment/directive-user-payment-history.html',
            controller: function ($scope, AccountingService, UserService, $filter) {
                $scope.currency = App.CONFIG.CURRENCY;
                $scope.reload = function () {
                    AccountingService.byUser($scope.user.id, function (data) {
                        $scope.history = data;
                        UserService.balance($scope.user.id, function (balance) {
                            $scope.balance = $filter('number')(balance, 2);
                        });
                    });
                };
                $scope.$watch('user', function (newValue) {
                    $scope.history = [];
                    if (newValue) {
                        $scope.user = newValue;
                        $scope.reload();
                    }
                });
            }
        };
    });
}());