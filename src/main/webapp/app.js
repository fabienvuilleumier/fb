(function () {
    'use strict';

    angular.module('Fablab', [
        //ext-lib
        'ngRoute', 'ngSanitize', 'ngResource', 'ui.bootstrap', 'btford.modal', 'ngNotify',
        'pascalprecht.translate', 'ngTable', 'ui.calendar', 'ui.select', 'vcRecaptcha',
        // Core
        'Notification', 'Loader', 'httpInterceptor'
    ]).config(function ($routeProvider, $httpProvider, $translateProvider) {
        $routeProvider.when('/', {
            templateUrl: './components/dashboard/home-view.html',
            controller: 'DashboardHomeController'
        }).when('/login', {
            templateUrl: './components/auth/login-view.html',
            controller: 'AuthLoginController'
        }).when('/logout', {
            templateUrl: './components/auth/logout-view.html',
            controller: 'AuthLogoutController'
        }).when('/signup', {
            templateUrl: './components/user/signup-view.html',
            controller: 'AuthSignUpController'
        }).when('/forgotPassword', {
            templateUrl: './components/auth/forgot-password-view.html',
            controller: 'AuthForgotPasswordController'
        }).when('/profil', {
            templateUrl: './components/user/profil-view.html',
            controller: 'UserProfilController'
        }).when('/configurations', {
            templateUrl: './components/configuration/configuration-list-view.html',
            controller: 'ConfigurationListController'
        }).when('/configurations/configuration-edit/:id', {
            templateUrl: './components/configuration/configuration-edit-view.html',
            controller: 'ConfigurationEditController'
        }).when('/users', {
            templateUrl: './components/user/list-view.html',
            controller: 'UserListController'
        }).when('/users/edit/:id', {
            templateUrl: './components/user/edit-view.html',
            controller: 'UserEditController'
        }).when('/users/edit', {
            templateUrl: './components/user/edit-view.html',
            controller: 'UserNewController'
        }).when('/reservations', {
            templateUrl: './components/reservation/list-view.html',
            controller: 'ReservationListController'
        }).when('/reservations/edit/:id', {
            templateUrl: './components/reservation/edit-view.html',
            controller: 'ReservationEditController'
        }).when('/reservations/edit', {
            templateUrl: './components/reservation/edit-view.html',
            controller: 'ReservationNewController'
        }).when('/payments', {
            templateUrl: './components/payment/by-user-view.html',
            controller: 'PaymentByUserController'
        }).when('/payments/:id', {
            templateUrl: './components/payment/by-user-view.html',
            controller: 'PaymentByUserController'
        }).when('/accounting', {
            templateUrl: './components/accounting/accounting-list-view.html',
            controller: 'AccountingListController'
        }).when('/machines', {
            templateUrl: './components/machine/machine-list-view.html',
            controller: 'MachineListController'
        }).when('/machines/machine-edit/:id', {
            templateUrl: './components/machine/machine-edit-view.html',
            controller: 'MachineEditController'
        }).when('/machines/machine-edit', {
            templateUrl: './components/machine/machine-edit-view.html',
            controller: 'MachineNewController'
        }).when('/machineTypes', {
            templateUrl: './components/machineType/machineType-list-view.html',
            controller: 'MachineTypeListController'
        }).when('/machineTypes/machineType-edit/:id', {
            templateUrl: './components/machineType/machineType-edit-view.html',
            controller: 'MachineTypeEditController'
        }).when('/machineTypes/machineType-edit', {
            templateUrl: './components/machineType/machineType-edit-view.html',
            controller: 'MachineTypeNewController'
        }).when('/machineStates', {
            templateUrl: './components/machineState/machineState-list-view.html',
            controller: 'MachineStateListController'
        }).when('/machineStates/machineState-edit/:id', {
            templateUrl: './components/machineState/machineState-edit-view.html',
            controller: 'MachineStateEditController'
        }).when('/machineStates/machineState-edit', {
            templateUrl: './components/machineState/machineState-edit-view.html',
            controller: 'MachineStateNewController'
        }).when('/machineStatus', {
            templateUrl: './components/machineStatus/machineStatus-list-view.html',
            controller: 'MachineStatusListController'
        }).when('/machineStatus/machineStatus-edit/:id', {
            templateUrl: './components/machineStatus/machineStatus-edit-view.html',
            controller: 'MachineStatusEditController'
        }).when('/machineStatus/machineStatus-edit', {
            templateUrl: './components/machineStatus/machineStatus-edit-view.html',
            controller: 'MachineStatusNewController'
        }).when('/membershipTypes', {
            templateUrl: './components/membershipType/membershipType-list-view.html',
            controller: 'MembershipTypeListController'
        }).when('/membershipTypes/membershipType-edit/:id', {
            templateUrl: './components/membershipType/membershipType-edit-view.html',
            controller: 'MembershipTypeEditController'
        }).when('/membershipTypes/membershipType-edit', {
            templateUrl: './components/membershipType/membershipType-edit-view.html',
            controller: 'MembershipTypeNewController'
        }).when('/revisions', {
            templateUrl: './components/revision/revision-list-view.html',
            controller: 'RevisionListController'
        }).when('/revisions/revision-edit/:id', {
            templateUrl: './components/revision/revision-edit-view.html',
            controller: 'RevisionEditController'
        }).when('/revisions/revision-edit', {
            templateUrl: './components/revision/revision-edit-view.html',
            controller: 'RevisionNewController'
        }).when('/revisions/revision-edit-code/:code', {
            templateUrl: './components/revision/revision-edit-view.html',
            controller: 'RevisionNewCodeController'
        }).when('/ticketStatus', {
            templateUrl: './components/ticketStatus/ticketStatus-list-view.html',
            controller: 'TicketStatusListController'
        }).when('/ticketStatus/ticketStatus-edit/:id', {
            templateUrl: './components/ticketStatus/ticketStatus-edit-view.html',
            controller: 'TicketStatusEditController'
        }).when('/ticketStatus/ticketStatus-edit', {
            templateUrl: './components/ticketStatus/ticketStatus-edit-view.html',
            controller: 'TicketStatusNewController'
        }).when('/tickets', {
            templateUrl: './components/ticket/ticket-list-view.html',
            controller: 'TicketListController'
        }).when('/tickets/ticket-edit/:id', {
            templateUrl: './components/ticket/ticket-edit-view.html',
            controller: 'TicketEditController'
        }).when('/tickets/ticket-edit', {
            templateUrl: './components/ticket/ticket-edit-view.html',
            controller: 'TicketNewController'
        }).otherwise({
            redirectTo: '/'
        });

        // HTTP Interceptor
        $httpProvider.interceptors.push('httpInterceptor');
        $translateProvider.preferredLanguage('en');
        $translateProvider.useSanitizeValueStrategy('escaped');
    }).run(function ($log, LoaderService, NotificationService, $rootScope, $location, AuthService) {
        App.interceptors.errorInterceptor.loaderService = LoaderService;
        App.interceptors.errorInterceptor.notificationService = NotificationService;

        $rootScope.updateCurrentUser = function () {
            AuthService.getCurrentUser(function (data) {
                $rootScope.connectedUser = data;
                $rootScope.$broadcast('connectedUserChanged', data);
                authRedirect();
            });
        };

        $rootScope.connectedUser = App.connectedUser;

        var authRedirect = function () {
            if ($rootScope.connectedUser) {
                if (!$rootScope.isAuthenticated()) {
                    //$location.path('/login');
                } else {
                    if ($location.$$path === '/login') {
                        $location.path('/');
                    }
                }
            }
        };

        // register listener to watch route changes
        $rootScope.$on("$routeChangeStart", function (event, next, current) {
            authRedirect();

            var path = next.originalPath;
            if (path) {
                var secondSlah = path.indexOf('/', 2);
                if (secondSlah === -1) {
                    secondSlah = path.length;
                }
                $rootScope.navModuleName = path.substring(1, secondSlah);

            }

        });

        $rootScope.isAuthenticated = function () {
            return $rootScope.connectedUser && $rootScope.connectedUser.connected;
        };

        /**
         * Has the current user any of this roles
         * @returns Boolean true if any role found
         */
        $rootScope.hasAnyRole = function () {
            for (var i = 0; i < arguments.length; i++) {
                if ($rootScope.hasRole(arguments[i])) {
                    return true;
                }
            }
            return false;
        };

        /**
         * Has the current user this role
         * @param role String role role to test
         * @returns Boolean true if he has the role, false otherwise
         */
        $rootScope.hasRole = function (role) {
            if (!$rootScope.connectedUser || !$rootScope.connectedUser.roles) {
                return false;
            }
            role = 'ROLE_' + role;
            for (var k in $rootScope.connectedUser.roles) {
                if ($rootScope.connectedUser.roles[k] === role) {
                    return true;
                }
            }
            return false;
        };
    });
}());