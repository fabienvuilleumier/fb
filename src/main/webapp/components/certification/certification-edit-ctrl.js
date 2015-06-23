'use strict';
var app = angular.module('Fablab');
app.controller('GlobalCertificationEditController', function ($scope, $location,
        CertificationService, NotificationService, StaticDataService) {
    $scope.currency = App.CONFIG.CURRENCY;
    $scope.selected = {certification: undefined};
    $scope.loadCertification = function (id) {
        CertificationService.get(id, function (data) {
            $scope.certification = data;
        });
    };
    $scope.save = function () {
        if (newCertification) {
            var certificationCurrent = angular.copy($scope.certification);
            CertificationService.save(certificationCurrent, function (data) {
                $scope.certification = data;
                NotificationService.notify("success", "certification.notification.saved");
                $location.path("certifications");
            });
        } else {
            var certificationCurrent = angular.copy($scope.certification);
            CertificationService.save(certificationCurrent, function (data) {
                $scope.certification = data;
                NotificationService.notify("success", "certification.notification.saved");
                $location.path("certifications");
            });
        }
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
    StaticDataService.loadTrainings(function (data) {
        $scope.trainingList = data;
    });
}
);
app.controller('CertificationNewController', function ($scope, $controller) {
    $controller('GlobalCertificationEditController', {$scope: $scope});
    $scope.newCertification = true;
    $scope.certification = {
        certificationDate: new Date()
    };
}
);
app.controller('CertificationEditController', function ($scope, $routeParams, $controller) {
    $controller('GlobalCertificationEditController', {$scope: $scope});
    $scope.newCertification = false;
    $scope.loadCertification($routeParams.id);
}
);

