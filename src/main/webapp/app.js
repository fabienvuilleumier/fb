angular.module('App', [
	//ext-lib
	'ngRoute', 'ngSanitize', 'ngResource', 'ngAnimate', 'ngToast', 'ui.bootstrap', 'btford.modal',
	'pascalprecht.translate', 'ngTable',
	// Core
	'Notification', 'Loader', 'httpInterceptor',
	'FabNaviguation',
	//Auth
	'Auth',
	//DashBoard
	'Dashboard',
	// Users
	'UserCtrls', 'UserFilters', 'User',

]).config(['$routeProvider', '$httpProvider', '$translateProvider',
	function ($routeProvider, $httpProvider, $translateProvider) {
		$routeProvider.when('/', {
			templateUrl: './components/dashboard/home-view.html',
			controller: 'DashboardHomeController'
		}).when('/login', {
			templateUrl: './components/auth/login-view.html',
			controller: 'AuthLoginController'
		}).when('/users', {
			templateUrl: './components/user/view/list.html',
			controller: 'UserListController'
		}).when('/users/edit/:id', {
			templateUrl: './components/user/view/edit.html',
			controller: 'UserEditController'
		}).when('/users/edit', {
			templateUrl: './components/user/view/edit.html',
			controller: 'UserNewController'
		}).otherwise({
			redirectTo: '/'
		});

		// HTTP Interceptor
		$httpProvider.interceptors.push('httpInterceptor');
		$translateProvider.preferredLanguage('en');
	}])
		.run(['LoaderService', 'NotificationService', '$rootScope', '$location', 'AuthService',
			function (LoaderService, NotificationService, $rootScope, $location, AuthService) {
				App.interceptors.errorInterceptor.loaderService = LoaderService;
				App.interceptors.errorInterceptor.notificationService = NotificationService;

				$rootScope.updateCurrentUser = function () {
					AuthService.getCurrentUser(function (data) {
						$rootScope.connectedUser = data;
						authRedirect();
					});
				};

				$rootScope.updateCurrentUser();
				
				var authRedirect = function(){
					if (!$rootScope.isAuthenticated()) {
						$location.path('/login');
					}else{
						if($location.$$path === '/login'){
							$location.path('/');
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
				 * @param String role...
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
				 * @param String role role to test
				 * @returns Boolean true if he has the role, false otherwise
				 */
				$rootScope.hasRole = function (role) {
					if (!$rootScope.user) {
						return false;
					}

					role = 'ROLE_' + role;
					for (var k in $rootScope.user.roles) {
						var value = $rootScope.user.roles[k];
						if (k === role) {
							return value;
						}
					}
					$log.error("Unkonwn role " + role);
					return false;
				};

			}]);