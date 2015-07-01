'use strict';
var app = angular.module('Fablab');
app.controller('GlobalUserPaymentEditController', function ($scope, $location, $window, $rootScope, $filter,
        UserPaymentService, NotificationService, StaticDataService) {
    $scope.selected = {userPayment: undefined};
    $scope.currency = App.CONFIG.CURRENCY;
    $scope.showRole = $rootScope.hasAnyRole('PAYMENT_MANAGE');
    $scope.btnTitle = $filter('translate')('userPayment.btnTitle');
    $scope.loadUserPayment = function (id) {
        UserPaymentService.get(id, function (data) {
            $scope.userPayment = data;
        });
    };
    $scope.save = function () {
        var userPaymentCurrent = angular.copy($scope.userPayment);
        UserPaymentService.save(userPaymentCurrent, function (data) {
            $scope.userPayment = data;
            NotificationService.notify("success", "userPayment.notification.saved");
            $location.path("userPayments");
        });
    };

    $scope.updatePrice = function () {
        var interTotal = parseFloat($scope.userPayment.amount);
        if (!$scope.userPayment.discount) {
            $scope.userPayment.total = interTotal;
        } else {
            if ($scope.userPayment.discountPercent) {
                var discountInter = parseFloat(interTotal) * (parseFloat($scope.userPayment.discount) / parseFloat(100));
                var total = parseFloat(interTotal) - parseFloat(discountInter);
                //0.05 cts ceil
                var val = $window.Math.ceil(total * 20) / 20;
                $scope.userPayment.total = parseFloat($filter('number')(val, 2));
            } else {
                var total = parseFloat(interTotal) - parseFloat($scope.userPayment.discount);
                //0.05 cts ceil
                var val = $window.Math.ceil(total * 20) / 20;
                $scope.userPayment.total = parseFloat($filter('number')(val, 2));
            }
        }

    };

    $scope.firstPercent = App.CONFIG.FIRST_PERCENT.toUpperCase() === "PERCENT";

    $scope.optionsPercent = [{
            name: "%",
            value: true
        }, {
            name: App.CONFIG.CURRENCY,
            value: false
        }];

    $scope.miniDate = new Date();
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
    StaticDataService.loadUsers(function (data) {
        $scope.userList = data;
    });
    StaticDataService.loadCashiers(function (data) {
        $scope.cashierList = data;
    });
}
);
app.controller('UserPaymentNewController', function ($scope, $controller, $rootScope) {
    $controller('GlobalUserPaymentEditController', {$scope: $scope});
    $scope.newUserPayment = true;
    $scope.paidDirectly = false;
    $scope.editable = true;
    $scope.userPayment = {
        datePayment: new Date(),
        user: $rootScope.connectedUser.user,
        payedForFabLab: false
    };
}
);
app.controller('UserPaymentEditController', function ($scope, $routeParams, $controller) {
    $controller('GlobalUserPaymentEditController', {$scope: $scope});
    $scope.newUserPayment = false;
    $scope.false = true;
    $scope.loadUserPayment($routeParams.id);
}
);
app.controller('UserPaymentRefundController', function ($scope, $rootScope, $controller, UserService, $filter) {
    $controller('GlobalUserPaymentEditController', {$scope: $scope});
    $scope.newUserPayment = true;
    $scope.paidDirectly = true;
    $scope.editable = true;
    
    UserService.balance($rootScope.connectedUser.user.id, function (balance) {
        var ref = balance < 0 ? 'REFUND' : 'CREDIT';
        if(parseFloat(balance) === parseFloat(0)){
            ref = 'CREDIT';
        }
        $scope.refund = ref;
        var cred = $filter('translate')('userPayment.cred');
        var refu = $filter('translate')('userPayment.refu');
        if (ref === 'CREDIT') {
            $scope.userPayment = {
                datePayment: new Date(),
                user: $rootScope.connectedUser.user,
                payedForFabLab: false,
                label: cred,
                refund: ref
            };
        } else {
            $scope.userPayment = {
                datePayment: new Date(),
                user: $rootScope.connectedUser.user,
                payedForFabLab: false,
                refund: ref,
                label: refu,
                amount: $filter('number')(-balance, 2),
                total: $filter('number')(-balance, 2)
            };
        }
    });

}
);

