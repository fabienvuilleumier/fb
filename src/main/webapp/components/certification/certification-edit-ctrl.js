'use strict';
var app = angular.module('Fablab');
app.controller('GlobalCertificationEditController', function ($scope, $routeParams, $location,
        CertificationService, NotificationService, StaticDataService, UserService) {
    $scope.currency = App.CONFIG.CURRENCY;
    $scope.fromTraining = $routeParams.trainingId ? true : false;
    $scope.selected = {certification: undefined};
    $scope.loadCertification = function (id) {
        CertificationService.get(id, function (data) {
            $scope.certification = data;
            setLists();
        });
    };
    $scope.save = function () {
        if ($scope.newCertification) {
            var certificationCurrent = angular.copy($scope.certification);
            CertificationService.save(certificationCurrent, function (data) {
                $scope.certification = data;
                NotificationService.notify("success", "certification.notification.saved");
                CertificationService.getId(data.training.name, function (withId) {
                    $location.path("certifications/certification-edit/" + withId.id);
                });
            });
        } else {
            $scope.certification.users = $scope.certifiedUsers;
            var certificationCurrent = angular.copy($scope.certification);
            CertificationService.save(certificationCurrent, function (data) {
                $scope.certification = data;
                NotificationService.notify("success", "certification.notification.saved");
                $location.path("certifications");
            });
        }
    };


    $scope.uploadPrice = function () {
        if ($scope.certification.training) {
            $scope.certification.certificationPrice = $scope.certification.training.price;
        }
    };

    $scope.settings = {
        bootstrap2: false,
        moveOnSelect: true,
        postfix: '_helperz',
        selectMinHeight: 200,
        filter: true,
        filterValues: true
    };
    var setLists = function () {
        UserService.list(function (users) {
            if ($scope.certification) {
                var availableUsers = [];
                var certifiedUsers = $scope.certification.users;
                var ari;
                for (ari = 0; ari < users.length; ari++) {
                    if (certifiedUsers.id !== users[ari].id) {
                        availableUsers.push(users[ari]);
                    }
                }
                $scope.availableUsers = availableUsers;
                $scope.certifiedUsers = certifiedUsers;
            }
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
app.controller('CertificationNewWithTrainingController', function ($scope, $routeParams, $controller, TrainingService) {
    $controller('GlobalCertificationEditController', {$scope: $scope});
    $scope.newCertification = true;
    $scope.certification = {
        certificationDate: new Date()
    };
    TrainingService.get($routeParams.trainingId, function (data) {
        $scope.certification.training = data;
        $scope.certification.certificationPrice = data.price;
    });
}
);

