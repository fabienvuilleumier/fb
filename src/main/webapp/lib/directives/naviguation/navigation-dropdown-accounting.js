var naviguation = angular.module('Fablab');
naviguation.directive('fabNavDropdownAccounting', function () {
    return {
        restrict: 'EA',
        scope: {
            icon: '@',
            label: '@',
            show: '='
        },
        template: '<a data-toggle="collapse" data-target="#accountingListDD" ng-show="show===undefined || show" href="javascript:;">'
                + '	<span class="glyphicon glyphicon-{{icon}}"></span> {{label | translate}}'
                + '     <b class="caret"></b>'
                + '</a>'
                + '<ul id="accountingListDD" class="collapse">'
                + '     <li fab-nav-item-af link="accounting" '
                + '         label="menu.inOut" icon="exchange" '
                + '         show="hasAnyRole(\'ACCOUNTING_VIEW\')"></li>'
                + '</ul>'
    };
});