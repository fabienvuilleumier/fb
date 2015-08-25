var naviguation = angular.module('Fablab');
naviguation.directive('fabNavItemAf', function () {
	return {
		restrict: 'EA',
		scope: {
			link: '@',
			icon: '@',
			label: '@',
			show: '='
		},
		template: '<a ng-show="show===undefined || show" href="#/{{link}}">'
				+ '	<i class="fa fa-fw fa-{{icon}}"></i> {{label | translate}}'
				+ '</a>'
	};
});

