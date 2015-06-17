var naviguation = angular.module('Fablab');
naviguation.directive('fabNavDropdownSupply', function () {
    return {
        restrict: 'EA',
        scope: {
            icon: '@',
            label: '@',
            show: '='
        },
        template: '<a data-toggle="collapse" data-target="#supplyListDD" ng-show="show===undefined || show" href="javascript:;">'
                + '	<i class="fa fa-fw fa-{{icon}}"></i> {{label | translate}}'
                + '     <b class="caret"></b>'
                + '</a>'
                + '<ul id="supplyListDD" class="collapse">'
                + '     <li fab-nav-item-af link="supplies" icon="wrench" '
                + '         label="menu.supplies" '
                + '         show="hasAnyRole(\'SUPPLY_MANAGE\')"></li>'
                + '     <li fab-nav-item-af link="supplyTypes" icon="newspaper-o" '
                + '         label="menu.supplyTypes" '
                + '         show="hasAnyRole(\'SUPPLY_MANAGE\')"></li>'
                + '     <hr show="hasAnyRole(\'SUPPLY_MANAGE\')"></hr>'
                + '     <li fab-nav-item-af link="purchases" icon="barcode" '
                + '         label="menu.purchases" '
                + '         show="hasAnyRole(\'SUPPLY_VIEW\')"></li>'
                + '</ul>'
    };
});


