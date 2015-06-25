var naviguation = angular.module('Fablab');
naviguation.directive('fabNavSide', function () {
    return {
        restrict: 'EA',
        scope: {
            show: '='
        },
        template: 
                '<ul ng-show="show===undefined || show" class="nav navbar-nav side-nav"> '
                + '  <li fab-nav-dropdown-machine  '
                + '    icon="connectdevelop" '
                + '    label="menu.machines" '
                + '    show="hasAnyRole(\'MACHINE_VIEW\')"></li> '
                + '  <li fab-nav-dropdown-training  '
                + '    icon="briefcase" '
                + '    label="menu.trainings" '
                + '    show="hasAnyRole(\'TRAINING_VIEW\')"></li> '
                + '  <li fab-nav-dropdown-supply class="dropdown" '
                + '    icon="barcode" '
                + '    label="menu.supplies" '
                + '    show="hasAnyRole(\'SUPPLY_VIEW\')"></li>'
                + '  <li fab-nav-dropdown-accounting class="dropdown" '
                + '    icon="euro" '
                + '    label="menu.accounting" '
                + '    show="hasAnyRole(\'ACCOUNTING_MANAGE\')"></li>'
                + '  <li fab-nav-item link="reservations" icon="calendar" '
                + '    label="menu.reservation" '
                + '    show="hasAnyRole(\'RESERVATION_VIEW\')"></li> '
                + '  <li fab-nav-item link="payments" icon="shopping-cart" '
                + '    label="menu.payment" '
                + '    show="hasAnyRole(\'PAYMENT_VIEW\')"></li> '
                + '  <li> '
                + '    <img src="./images/logoFabLabManager.png" alt="logo" height="220" width="220" id="faviconLeft" class="img-circle"/> '
                + '  </li> '
                + '</ul> </div>'
    };
});

